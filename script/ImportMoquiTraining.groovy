import java.nio.file.Files
import java.nio.file.Paths

// Path to the CSV file
String filePath = "component://moqui-training/data/MoquiTrainingDummyData.csv"

// Read the file
List<String> lines = Files.readAllLines(Paths.get(ec.resource.getLocation(filePath)))

// Skip the header row and process each line
lines.subList(1, lines.size()).each { line ->
    String[] values = line.split(",")
    ec.entity.make("MoquiTraining")
            .set("trainingId", values[0].trim().toInteger())
            .set("trainingName", values[1].trim())
            .set("trainingDate", values[2].trim())
            .set("trainingPrice", values[3].trim().toBigDecimal())
            .set("trainingDuration", values[4].trim().toInteger())
            .create()
}

println "Data imported successfully!"