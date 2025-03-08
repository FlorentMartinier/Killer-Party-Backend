$paths = ".\node_modules\", ".\frontend\killer-party-front\node_modules\", ".\frontend\killer-party-front\dist\", ".\src\main\resources\static"
foreach ($filePath in $paths)
{
    if (Test-Path $filePath)
    {
        Remove-Item -r -fo $filePath
        Write-Host "Path $filePath removed"
    }
    else
    {
        Write-Host "Path $filePath doesn't exits"
    }
}
./gradlew clean
./gradlew build
java -jar build/libs/killerpartyback-0.0.1-SNAPSHOT.jar