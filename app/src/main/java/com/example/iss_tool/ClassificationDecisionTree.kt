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
    val leftIconLabel: String? = null,
    val rightIconLabel: String? = null,
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

private var rightLeaf = ClassificationLeaf(
    category = "Exception",
    additionalInfo = "The material/substance is not subject to any transport regulations (unless transported together with other dangerous goods.)"
)

private var leftLeftLeaf = ClassificationLeaf(
    category = "Test Category T1",
    unNumber = 1337,
    unSubstance = "Test T1"
)

private var leftRightLeaf = ClassificationLeaf(
    category = "Test Category T2",
    unNumber = 4242,
    unSubstance = "Test T2"
)

private var leftNode = ClassificationNode(
    question = "Does it meet or is it suspected to meet the definition of a Category A infectious substance?",
    additionalInfo = "Category A: An infectious substance which is transported in a form that, when exposure to it occurs, is capable of causing permanent disability, life-threatening   or fatal disease in otherwise healthy humans or animals.",
    left = leftLeftLeaf,
    right = leftRightLeaf
)

var classificationDecisionTree = ClassificationNode(
    question = "Could the Specimen or Substance be Infectious?",
    leftIconLabel = "Yes",
    rightIconLabel = "No",
    left = leftNode,
    right = rightLeaf
)


