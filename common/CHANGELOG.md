# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.0.0-beta] - 2026-06-13

### Added

- Restored Forge loader support for 26.1.2.
- Implement configurable minimum spawn heights and dynamic ore generation chances.
- Added block tags for replaceable blocks instead of hardcoded sets.

### Changed

- Ported project to Minecraft 26.1.2 (NeoForge 26.1.2.75 / Forge 64.0.8).
- Updated build configurations to run on Gradle 9.5.0 and Java 25.
- Upgraded Forge module to use ForgeGradle 7.0.25.
- [Datapack] now supports hex color.
- Ore Creepers explosions colors to be paler.
- Refactored ModList usage to static calls in Forge platform.

### Fixed

- Fixed `ModModelProvider` compilation errors with Material API.
- Fixed Forge Access Transformers to use Mojang mappings.
- Cleaned up obsolete mixins and configurations.