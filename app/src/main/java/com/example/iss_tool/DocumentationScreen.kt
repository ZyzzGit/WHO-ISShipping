package com.example.iss_tool

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider.getUriForFile
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.iss_tool.theme.customColorScheme
import com.example.iss_tool.theme.customTypography
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDComboBox
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDTextField
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.time.LocalDate


@Composable
fun DocumentationScreen(
    navController: NavController,
    modifier: Modifier,
    category: Category,
    unNumber: Int?,
    unSubstance: UnSubstance,
    quantity: Int?,
    iceQuantity: Int,
    shippingMethod: ShippingMethod,
    shipperName: String,
    shipperAddress: String,
    receiverName: String,
    receiverAddress: String,
    substanceName: String?, responsibleName: String?, responsiblePhone: String? // only provided for Category A
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {
        Text(text = "Documentation", style = customTypography.bodyLarge, color = customColorScheme.primary)
        val context = LocalContext.current

        var documentDGTD: PDDocument? by remember { mutableStateOf(null) }
        var downloadPath: String? by remember { mutableStateOf(null ) }

        // Dangerous goods declaration required only for category A substances and biological/clinical/medical wastes (UN 3291)
        if (category == Category.A || unNumber == 3291) {
            LaunchedEffect(Unit) {
                documentDGTD = getFilledDangerousGoodDeclaration(
                    context, category, unNumber, unSubstance, quantity, iceQuantity, shippingMethod, shipperName, shipperAddress, receiverName, receiverAddress, substanceName, responsibleName, responsiblePhone
                )
            }
        }

        if (documentDGTD != null && downloadPath == null) {
            OutlinedButton(
                onClick = {
                    try {
                        val fileName = "FilledDGTD.pdf"
                        documentDGTD!!.isAllSecurityToBeRemoved = true
                        downloadPath = downloadPdf(context, fileName, documentDGTD!!)
                        documentDGTD!!.close()
                    } catch (error: Throwable) {
                        throw Error("Download failed: $error")
                    }
                },
                modifier.align(Alignment.Start),
                enabled = !documentDGTD!!.document.isClosed
            ) {
                Text(text = "Download DGTD")
            }
        }

        if (downloadPath != null) {
            Spacer(modifier = Modifier.height(70.dp))
            DownloadCompleteIcon(modifier = Modifier.padding(10.dp))
            Text(
                text = "Download completed!",
                style = customTypography.bodyLarge,
                color = customColorScheme.primary
            )
            Text(
                text = "Open ${downloadPath!!.substring(downloadPath!!.lastIndexOf('/') + 1)}",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        openPdfExternally(context, downloadPath!!)
                    },
                style = customTypography.bodyLarge,
                color = customColorScheme.primary
            )
        }

        IconBody(iconId = R.drawable.info_icon, text = "Always check if there is any state variation and/or operator variations")
    }
}


private fun getFilledDangerousGoodDeclaration(
    context: Context,
    category: Category,
    unNumber: Int?,
    unSubstance: UnSubstance,
    quantity: Int?,
    iceQuantity: Int,
    shippingMethod: ShippingMethod,
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
        un.value = "UN\n${unNumber ?: '-'}"
        shippingName.value = unSubstance.toString()
        classDivision.value = "6.2"
        quantityAndPackagingType.value = if (quantity != null) "${quantity}ml" else "-"
        packagingInstructions.value = "620"
        signatoryName.value = shipperName
        signatureDate.value = LocalDate.now().toString()

        // Additional info for category A only
        if (category == Category.A) {
            shippingName.value = shippingName.value + "\n($substanceName)"
            responsiblePerson.value = "Responsible person: $responsibleName\nPhone: $responsiblePhone"
        }

        // Fill shipment limitations
        when (shippingMethod) {
            ShippingMethod.CargoOnly -> {
                cargoOnly.setValue(cargoOnly.options[0])
                passengerAndCargo.setValue(cargoOnly.options[1])
            }
            ShippingMethod.Passenger -> {
                cargoOnly.setValue(cargoOnly.options[1])
                passengerAndCargo.setValue(cargoOnly.options[0])
            }
            else -> {
                cargoOnly.setValue(cargoOnly.options[1])
                passengerAndCargo.setValue(cargoOnly.options[1])
            }
        }

        // Fill extra row when shipping with ice
        if (iceQuantity > 0) {
            un.value = un.value + "\n\n\nUN\n1845"
            shippingName.value = shippingName.value + "\n\n\nDry ice"
            classDivision.value = classDivision.value + "\n\n\n\n9"
            quantityAndPackagingType.value = quantityAndPackagingType.value + "\n\n\n\n${iceQuantity}kg"
            packagingInstructions.value = packagingInstructions.value + "\n\n\n\n954"
        }

        // Assume always packing in single fiberboard box
        quantityAndPackagingType.value = quantityAndPackagingType.value + "\n\nAll packed in one fiberboard box"

        return document
    } catch (error: Throwable) {
        throw Error("Pdf could not be loaded: $error")
    }
}


fun downloadPdf(context: Context, fileName: String, document: PDDocument): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    document.save(byteArrayOutputStream)

    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
    }

    val resolver = context.contentResolver
    val uri: Uri? = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)

    uri?.let { outputFileUri ->
        // Write to downloads folder
        context.contentResolver.openOutputStream(outputFileUri).use { outputStream ->
            outputStream?.write(byteArrayOutputStream.toByteArray())
            outputStream?.flush()
        }

        // Get the file path from the URI
        val cursor = context.contentResolver.query(outputFileUri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndex(MediaStore.MediaColumns.DATA)
                if (columnIndex != -1) {
                    return it.getString(columnIndex) // Return the file path pdf was stored to
                }
            }
        }
        // If URI or file path retrieval fails
        throw IllegalStateException("downloadPdf: Failed to retrieve file path from URI: $outputFileUri")
    }
    // If URI is null
    throw IllegalStateException("downloadPdf: Failed to insert file into MediaStore")
}


fun openPdfExternally(context: Context, path: String) {
    try {
        val pdfFile = File(path)
        val contentUri: Uri = getUriForFile(context, context.packageName + ".provider", pdfFile)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(contentUri, "application/pdf")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(context, intent, null)
    } catch (e: Exception) {
        throw Exception("openPdfExternally: Failed to open pdf: $e")
    }
}


@Preview(name = "DocumentationScreenPreview")
@Composable
fun DocumentationScreenPreview() {
    val navController = rememberNavController()
    DocumentationScreen(
        navController = navController,
        modifier = Modifier,
        category = Category.A,
        unNumber = 2814,
        unSubstance = UnSubstance.ISHumans,
        quantity = 42,
        iceQuantity = 17,
        shippingMethod = ShippingMethod.Passenger,
        shipperName = "Nico",
        shipperAddress = "Examplestreet 7",
        receiverName = "WHO Italy",
        receiverAddress = "Via something 93",
        substanceName = "Kyasanur Forest disease virus",
        responsibleName = "Leonardo da Vinci",
        responsiblePhone = "+39 884794294"
    )
}
