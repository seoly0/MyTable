import java.net.URI

rootProject.name = "MyTable"

sourceControl {
    gitRepository(URI("https://github.com/seoly0/SpringBase.git")) {
        producesModule("me.seoly.spring:base")
    }
}

sourceControl {
    gitRepository(URI("https://github.com/seoly0/ModelMapper.git")) {
        producesModule("me.seoly.utils:modelmapper")
    }
}