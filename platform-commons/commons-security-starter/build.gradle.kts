dependencies {
    api(project(":platform-commons:commons-security"))
    api("org.springframework.boot:spring-boot-starter-security")
    api("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-security-starter")
}
