package com.example.iss_tool

/**
 * Marks the end of classification process, should be used as leafs for final ClassificationNode(s)
 * unNumber and unSubstance should be null for Category Exempt and "Category" Exception
 */
class ClassificationLeaf(
    val category: String,
    val unNumber: Int? = null,
    val unSubstance: String? = null,
    val additionalInfo: String? = null
)

/**
 *  Building block for decision tree used in ClassificationScreen
 *  members left and right must be of type ClassificationNode OR ClassificationLeaf
 *  icons and labels are chosen according to left/right nodes (or use default)
 */
class ClassificationNode(
    val question: String,
    val additionalInfo: String? = null,
    val leftIconId: Int? = R.drawable.check,    // default "yes"
    val rightIconId: Int? = R.drawable.close,   // default "no"
    val leftIconLabel: String? = "Yes",
    val rightIconLabel: String? = "No",
    val left: Any,
    val right: Any
) {
    init {
        require(left is ClassificationNode|| left is ClassificationLeaf) {
            "ClassificationNode.left must be either ClassificationNode or ClassificationLeaf"
        }
        require(right is ClassificationNode || right is ClassificationLeaf) {
            "ClassificationNode.right must be either ClassificationNode or ClassificationLeaf"
        }
    }
}

/**
 *  Build classification decision tree bottom up, starting with leaves
 *  all variables except final tree should be declared as private
 */

private var exceptionLeaf = ClassificationLeaf(
    category = "Exception",
    additionalInfo = "The material/substance is not subject to any transport regulations (unless transported together with other dangerous goods)."
)

private var infectiousAffectingHumansLeaf = ClassificationLeaf(
    category = "Category A",
    unNumber = 2814,
    unSubstance = "Infectious Substance Affecting Humans"
)

private var infectiousAffectingAnimalsOnlyLeaf = ClassificationLeaf(
    category = "Category A",
    unNumber = 2900,
    unSubstance = "Infectious Substance Affecting Animals Only"
)

private var exemptLeaf = ClassificationLeaf(
    category = "Exempt Human/Animal Specimen",
    additionalInfo = "Apply basic triple packaging system."
)

private var infectiousBiologicalLeaf = ClassificationLeaf(
    category = "Category B",
    unNumber = 3373,
    unSubstance = "Biological Substance"
)

private var infectiousWasteLeaf = ClassificationLeaf(
    category = "Category B",
    unNumber = 3291,
    unSubstance = "Infectious Waste",
    additionalInfo = "Biomedical Waste, n.o.s.\n" +
            "OR Clinical Waste, unspecified n.o.s.\n" +
            "OR Medical Waste, n.o.s."
)

private var categoryASplitNode = ClassificationNode(
    question = "Is exposure to the specimen likely to cause serious disease in healthy humans or animals?",
    leftIconId = R.drawable.animal_icon,
    rightIconId = R.drawable.human_icon,
    leftIconLabel = "Animals only",
    rightIconLabel = "Humans and animals",
    left = infectiousAffectingAnimalsOnlyLeaf,
    right = infectiousAffectingHumansLeaf
)

private var categoryBSplitNode = ClassificationNode(
    question = "Is the substance a biomedical, medical or clinical waste?",
    left = infectiousWasteLeaf,
    right = infectiousBiologicalLeaf
)

private var biologicalAgentsNode = ClassificationNode(
    question = "Does the material/substance contain only a minimal likelihood of biological agents or biological agents that are unlikely to cause illness in exposed humans/animals?",
    left = exemptLeaf,
    right = categoryBSplitNode
)

private var criticalBiologicalAgentsNode = ClassificationNode(
    question = "Is the material/substance known or reasonable expected to contain a biological agent capable of causing severe disability or life threatening or fatal illness in exposed humans or animals?",
    left = categoryASplitNode,
    right = biologicalAgentsNode
)

var classificationDecisionTree = ClassificationNode(
    question = "Could the material or substance be infectious?",
    left = criticalBiologicalAgentsNode,
    right = exceptionLeaf,
    additionalInfo = "These substances are not considered infectious:\n" +
            "• Sterile (free from biological agents)\n" +
            "• Neutralized/inactivated\n" +
            "• Environmental samples (e.g. food or water)\n" +
            "• A product for transplant/transfusion\n" +
            "• A dried blood spot\n" +
            "• A regulated biological product"
)


