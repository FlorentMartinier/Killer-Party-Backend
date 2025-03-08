$paths = ".\node_modules\", ".\frontend\killer-party-front\node_modules\", ".\frontend\killer-party-front\dist\", ".\src\main\resources\static"
foreach ($filePath in $paths)
{
    if (Test-Path $filePath)
    {
        Remove-Item -r -fo $filePath
    }
    else
    {
        Write-Host "Path doesn't exits"
    }
}
npm install ./frontend/killer-party-front
npm run build --prefix ./frontend/killer-party-front
./gradlew clean
./gradlew build
java -jar build/libs/killerpartyback-0.0.1-SNAPSHOT.jar