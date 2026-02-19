$env:JAVA_HOME = "C:\Program Files\Microsoft\jdk-21.0.10.7-hotspot"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
Write-Host "Using Java from: $env:JAVA_HOME"

# Build the application (skipping tests for speed in dev)
Write-Host "Building application..."
./mvnw clean package -DskipTests

# Run the jar
Write-Host "Starting application..."
java -jar target/event-platform-0.0.1-SNAPSHOT.jar
