#!/bin/bash
set -e

# -------------------------
# Find JAR
# -------------------------
JAR_FILE=$(find target -maxdepth 1 -type f -name "*.jar" ! -name "*original*" | head -n 1)

if [[ -z "$JAR_FILE" ]]; then
  echo "ERROR: JAR file not found!"
  echo "Building project with Maven to build the jar file..."
  echo "Please have java 17 and maven ready in your local..."
  mvn clean package -DskipTests
fi

echo "Found JAR: $JAR_FILE"
JAR_FILE=$(find target -maxdepth 1 -type f -name "*.jar" ! -name "*original*" | head -n 1)

# -------------------------
# Run the JAR
# -------------------------
JAVA_OPTS="${JAVA_OPTS:-"-Xms256m -Xmx1024m"}"
echo "Starting Spring Boot app..."
exec java $JAVA_OPTS -jar "$JAR_FILE"
