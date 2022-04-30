call crudrun
if "%ERRORLEVEL%" == "0" goto run-browser
echo.
echo CRUDRUN is not running correctly. Operation ended
goto fail

:run-browser
start http://localhost:8080/mycrud/v1/tasks

:fail
echo.
echo Errors found

:end
echo.
echo Operation ended