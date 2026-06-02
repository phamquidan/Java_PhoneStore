#!/bin/bash
echo "=========================================="
echo "  PhoneStore Application Run Script"
echo "=========================================="
echo ""

if [ -d "/c/Users/Admin/Downloads/jdk-21_windows-x64_bin/jdk-21.0.10" ]; then
    echo "[+] Auto-configured JAVA_HOME to JDK 21"
    export JAVA_HOME="/c/Users/Admin/Downloads/jdk-21_windows-x64_bin/jdk-21.0.10"
elif [ -d "c:/Users/Admin/Downloads/jdk-21_windows-x64_bin/jdk-21.0.10" ]; then
    echo "[+] Auto-configured JAVA_HOME to JDK 21"
    export JAVA_HOME="c:/Users/Admin/Downloads/jdk-21_windows-x64_bin/jdk-21.0.10"
fi

cd "$(dirname "$0")/phonestore"
echo "[+] Starting application on port 8080..."
echo ""
./mvnw spring-boot:run
