#!/bin/bash
# Build and run PhoneStore application

echo "=========================================="
echo "  PhoneStore Application Build Script"
echo "=========================================="
echo ""

# Navigate to project directory
cd "$(dirname "$0")/../phonestore"

echo "[1] Building project with Maven..."
mvn clean compile -DskipTests

if [ $? -eq 0 ]; then
    echo ""
    echo "[2] Build successful!"
    echo ""
    echo "[3] Starting application..."
    echo "    Make sure SQL Server database 'QL_CUAHANGDIENTHOAI' is running"
    echo ""
    mvn spring-boot:run
else
    echo "[!] Build failed!"
    exit 1
fi
