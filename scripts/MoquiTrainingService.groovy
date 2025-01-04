import org.moqui.context.ExecutionContext
import org.moqui.entity.EntityValue


def createMoquiTraining(ExecutionContext ec, String trainingName, String trainingDate) {


    if (!trainingName) {
        throw new IllegalArgumentException("trainingName is mandatory")
    }

    def dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy")
    dateFormat.setLenient(false)
    Date parsedDate = null
    try {
        parsedDate = dateFormat.parse(trainingDate)
    } catch (Exception e) {
        throw new IllegalArgumentException("trainingDate must follow MM/dd/yyyy format")
    }


    EntityValue moquiTraining = ec.entity.makeValue("moqui.training.MoquiTraining")
    moquiTraining.set("trainingName", trainingName)
    moquiTraining.set("trainingDate", parsedDate)



    moquiTraining.create()


    return moquiTraining.get("trainingId")
}

return createMoquiTraining(ec, trainingName, trainingDate)

//
//import org.moqui.context.ExecutionContext
//import org.moqui.entity.EntityValue
//import org.moqui.entity.EntityList
//
//
//def MoquiTrainingService(ExecutionContext ec, String trainingName, String trainingDate) {
//
//    if (!trainingName) {
//        throw new IllegalArgumentException("trainingName is mandatory")
//    }
//
//
//    def dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy")
//    dateFormat.setLenient(false)
//    Date parsedDate = null
//    try {
//        parsedDate = dateFormat.parse(trainingDate)
//    } catch (Exception e) {
//        throw new IllegalArgumentException("trainingDate must follow MM/dd/yyyy format")
//    }
//
//
//    EntityValue moquiTraining = ec.entity.find("MoquiTraining").makeValue()
//    moquiTraining.set("trainingName", trainingName)
//    moquiTraining.set("trainingDate", parsedDate)
//
//
//    moquiTraining.create()
//
//
//    return moquiTraining.get("trainingId")
//}
//
//
//def trainingName = "Advanced Java Training"
//def trainingDate = "01/10/2025"
//
//
//def trainingId = createMoquiTraining(ec, trainingName, trainingDate)
//
//return trainingId
