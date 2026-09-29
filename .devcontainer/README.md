# iPad / Codespaces setup

This repository is configured for GitHub Codespaces.

## Start it from an iPad

1. Open the repository on GitHub.
2. Tap **Code**.
3. Open **Codespaces**.
4. Choose **Create codespace on main**.
5. Wait for VS Code in the browser to load.

The container provides Java 21 and Gradle 8.8.

## Build the mod

In the Codespaces terminal:

```bash
gradle build --no-daemon --no-configuration-cache
```

The JAR files appear in:

```
build/libs/
```

GitHub Actions also builds the project automatically when you push changes.

## Important

Codespaces is a development computer in the cloud. It is excellent for editing and compiling the mod from an iPad, but it does not provide a normal graphical Minecraft client. Use the GitHub Actions artifact to get the compiled JAR for testing on a Minecraft device.
