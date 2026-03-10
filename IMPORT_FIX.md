# Java Import Fix Report

## Problem

The `deadlock` and `resource` packages could not resolve each other's imports.
Specifically, `Deadlocker.java` (`package deadlock`) imports `resource.Resource`,
which failed with `package resource does not exist` when compiled from inside
the `deadlock/` subdirectory, or in an IDE without a proper source-root configuration.

## Root Cause

Both directories declare Java packages (`package deadlock;`, `package resource;`).
Java package-based compilation requires the compiler to be invoked from the **project
root** — the directory that contains the package folders as direct children.
Without this, the compiler cannot locate sibling packages.

The same issue manifests in VS Code (with the Java Extension Pack) when there is
no `.vscode/settings.json` telling it where the source root is. By default the
extension treats each subdirectory as its own isolated source tree, so
`resource.Resource` appears missing from the `deadlock` package.

## Fix

### For VS Code (already applied)

A `.vscode/settings.json` was created at the project root with:

```json
{
    "java.project.sourcePaths": ["."]
}
```

This tells the Java extension to treat the project root (`java_multithreading/`)
as the source root, making all sibling packages visible to each other.
Reload the VS Code window after applying this change (`Ctrl+Shift+P` →
**Developer: Reload Window**).

### For command-line compilation

Always run `javac` from the project root, never from inside a package subdirectory:

```bash
# From java_multithreading/
javac resource/Resource.java deadlock/Deadlocker.java deadlock/DeadlockExample.java

java deadlock.DeadlockExample
```
