import org.moqui.context.ExecutionContext
import org.moqui.entity.EntityList
import org.moqui.entity.EntityCondition

// Define the service to fetch MoquiTraining records
def MoquiTrainingRecordsService(ExecutionContext ec, String trainingName, Integer trainingId) {
    // Build the condition for filtering
    def conditions = []

    if (trainingName) {
        conditions.add(EntityCondition.makeCondition("trainingName", EntityCondition.LIKE, "%" + trainingName + "%"))
    }

    if (trainingId) {
        conditions.add(EntityCondition.makeCondition("trainingId", EntityCondition.EQUALS, trainingId))
    }

    // Create the entity condition (AND conditions)
    def entityCondition = conditions ? EntityCondition.makeCondition(conditions) : null

    // Fetch records from the MoquiTraining entity
    EntityList trainingRecords = ec.entity.find("MoquiTraining")
            .condition(entityCondition)
            .queryList()

    // Extract only the needed fields (trainingId, trainingName, trainingDate)
    def resultList = []
    trainingRecords.each { record ->
        resultList.add([
                "trainingId": record.get("trainingId"),
                "trainingName": record.get("trainingName"),
                "trainingDate": record.get("trainingDate")
        ])
    }

    // Return the list of records
    return resultList
}

// Example usage (would be called within Moqui framework context)
////def trainingNameFilter = "Java"
//def trainingIdFilter = null  // can be set to an integer value for filtering by trainingId
//def trainingRecords = MoquiTrainingRecordsService(ec, trainingNameFilter, trainingIdFilter)

return trainingRecords
