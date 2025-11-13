@echo off
chcp 65001 >nul
cd /d D:\DoAnNhom5

echo === BIEN DICH ===
javac -d bin -sourcepath src src/Nhom5/*.java

if %errorlevel% neq 0 (
    echo.
    echo LOI BIEN DICH! Kiem tra code.
    pause
    exit
)

echo.
echo === CHAY CHUONG TRINH ===
java -cp bin Nhom5.Main
pause