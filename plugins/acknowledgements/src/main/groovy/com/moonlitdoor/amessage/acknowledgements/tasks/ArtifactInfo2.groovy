package com.moonlitdoor.amessage.acknowledgements.tasks

class ArtifactInfo2 {
  private String group
  private String name
  private String fileLocation
  private String version

  ArtifactInfo2(String group,
                String name,
                String fileLocation,
                String version) {
    this.group = group
    this.name = name
    this.fileLocation = fileLocation
    this.version = version
  }

  String getGroup() {
    return group
  }

  String getName() {
    return name
  }

  String getFileLocation() {
    return fileLocation
  }

  String getVersion() {
    return version
  }
}
