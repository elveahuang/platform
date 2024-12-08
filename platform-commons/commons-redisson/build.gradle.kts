dependencies {
    api("org.redisson:redisson")
    api("org.redisson:redisson-spring-data-33")
    api("io.netty:netty-resolver-dns")
    api("io.netty:netty-resolver-dns-native-macos")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-redisson")
}
