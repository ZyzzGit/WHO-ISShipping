package com.example.iss_tool

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDComboBox
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDTextField
import java.io.InputStream
import java.time.LocalDate

@Composable
fun DocumentationScreen(
    navController: NavController,
    modifier: Modifier,
    category: String,
    unNumber: Int,
    unSubstance: String,
    quantity: Int,
    ice: Int,
    shippingMethod: String,
    shipperName: String,
    shipperAddress: String,
    receiverName: String,
    receiverAddress: String,
    substanceName: String?, responsibleName: String?, responsiblePhone: String? // only provided for Category A
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Documentation", style = customTypography.displaySmall, color = customColorScheme.primary)
        val context = LocalContext.current
        val root = context.cacheDir

        var documentDGTD: PDDocument? by remember { mutableStateOf(null) }
        var documentAirWaybill: PDDocument? by remember { mutableStateOf(null) }
        var status by remember { mutableStateOf("") }

        // Dangerous goods declaration required only for category A substances and biological/clinical/medical wastes (UN 3291)
        if (category == "Category A" || unNumber == 3291) {
            LaunchedEffect(Unit) {
                documentDGTD = getFilledDangerousGoodDeclaration(
                    context, category, unNumber, unSubstance, quantity, ice, shippingMethod, shipperName, shipperAddress, receiverName, receiverAddress, substanceName, responsibleName, responsiblePhone
                )
            }
        }

        if (documentDGTD != null) {
            OutlinedButton(
                onClick = {
                    try {
                        val path = root.absolutePath + "/FilledDGTD.pdf"
                        documentDGTD!!.isAllSecurityToBeRemoved = true
                        documentDGTD!!.save(path)                       // TODO: Perhaps save to download folder, or let user decide destination
                        documentDGTD!!.close()
                        status = "Saved filled DGTD to $path"
                    } catch (error: Throwable) {
                        status = "Download failed: $error"
                    }
                },
                modifier.align(Alignment.Start),
                enabled = !documentDGTD!!.document.isClosed
            ) {
                Text(text = "Download DGTD")
            }
        }

        Text(text = status, style = customTypography.bodySmall)

        // TODO: similary fill and download documentAirWaybill

    }
}


private fun getFilledDangerousGoodDeclaration(
    context: Context,
    category: String,
    unNumber: Int,
    unSubstance: String,
    quantity: Int,
    ice: Int,
    shippingMethod: String,
    shipperName: String,
    shipperAddress: String,
    receiverName: String,
    receiverAddress: String,
    substanceName: String?,
    responsibleName: String?,
    responsiblePhone: String?
) : PDDocument {

    PDFBoxResourceLoader.init(context)
    val pdd: InputStream = context.resources.openRawResource(R.raw.shippers_declaration_dangerous_goods)

    try {
        // Load PDF
        val document = PDDocument.load(pdd)

        // Get form
        val docCatalog = document.documentCatalog
        val acroForm = docCatalog.acroForm

        // Get fields
        val page = acroForm.getField("Page __") as PDTextField
        val ofPages = acroForm.getField("of __ pages") as PDTextField
        val shipper = acroForm.getField("Shipper") as PDTextField
        val receiver = acroForm.getField("Consignee") as PDTextField
        val cargoOnly = acroForm.getField("Cargo Aircraft Only") as PDComboBox
        val passengerAndCargo = acroForm.getField("Passenger and Cargo Aircraft") as PDComboBox
        val un = acroForm.getField("UN or ID") as PDTextField
        val shippingName = acroForm.getField("Proper Shipping Name") as PDTextField
        val classDivision = acroForm.getField("Class or Division") as PDTextField
        val quantityAndPackagingType = acroForm.getField("Quantity and Type of Packing") as PDTextField
        val packagingInstructions = acroForm.getField("Packing Instruction") as PDTextField
        val responsiblePerson = acroForm.getField("Additional Handling Information") as PDTextField
        val signatoryName = acroForm.getField("Name-title") as PDTextField
        val signatureDate = acroForm.getField("place-date") as PDTextField

        // Fill fields
        page.value = "1"
        ofPages.value = "1"
        shipper.value = "$shipperName\n$shipperAddress"
        receiver.value = "$receiverName\n$receiverAddress"
        un.value = "UN\n$unNumber"
        shippingName.value = unSubstance
        classDivision.value = "6.2"
        quantityAndPackagingType.value = "${quantity}ml"
        packagingInstructions.value = "620"
        signatoryName.value = shipperName
        signatureDate.value = LocalDate.now().toString()

        // Additional info for category A only
        if (category == "Category A") {
            shippingName.value = shippingName.value + "\n($substanceName)"
            responsiblePerson.value = "Responsible person: $responsibleName\nPhone: $responsiblePhone"
        }

        // Fill shipment limitations
        if (shippingMethod == "CargoOnly") {
            cargoOnly.setValue(cargoOnly.options[0])
            passengerAndCargo.setValue(cargoOnly.options[1])
        } else if (shippingMethod == "Passenger") {
            cargoOnly.setValue(cargoOnly.options[1])
            passengerAndCargo.setValue(cargoOnly.options[0])
        } else {    // TODO: check if this case should be considered by the app
            cargoOnly.setValue(cargoOnly.options[1])
            passengerAndCargo.setValue(cargoOnly.options[1])
        }

        // Fill extra row when shipping with ice
        if (ice > 0) {
            un.value = un.value + "\n\n\nUN\n1845"
            shippingName.value = shippingName.value + "\n\n\nDry ice"
            classDivision.value = classDivision.value + "\n\n\n\n9"
            quantityAndPackagingType.value = quantityAndPackagingType.value + "\n\n\n\n${ice}kg"
            packagingInstructions.value = packagingInstructions.value + "\n\n\n\n954"
        }

        // Assume always packing in single fiberboard box
        quantityAndPackagingType.value = quantityAndPackagingType.value + "\n\nAll packed in one fiberboard box"

        return document
    } catch (error: Throwable) {
        throw Error("Pdf could not be loaded: $error")
    }
}

