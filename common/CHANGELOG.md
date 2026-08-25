# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.0.3] - 2026-08-25

### Fixed

- Fix Ore Creepers spawning regardless of light level by enforcing monster spawn rules in the Overworld (#10).

## [2.0.2] - 2026-07-09

### Changed

- Custom creepers are now loaded globally from the `config` folder instead of world datapacks to fix missing spawn eggs and commands.

## [2.0.1] - 2026-06-30

### Fixed

- Fix dynamic ore creeper types not loading in production jar files.
- Prevent integrated server crash when Ore Primed TNT explodes if no custom creepers are loaded.

## [2.0.0] - 2026-06-15

First official release out of beta.

### Added

- Support for custom colors using `skin_color` and `ore_color` in datapacks (no custom texture file needed!).

### Fixed

- Skip registering creepers and items if the mod they depend on is not installed.
- Stop creepers from leaving air holes when exploding if the mod/block they depend on is missing.

## [2.0.0-beta] - 2026-06-13

26.1.2 Port

### Changed

- Ore Creepers explosions colors to be paler.

### Fixed

- Cleaned up obsolete mixins and configurations.