import java.net.URI

rootProject.name = "MyTable"

sourceControl {
    gitRepository(URI("https://github.com/seoly0/SpringBase.git")) {
        producesModule("me.seoly.spring:base")
    }
}

sourceControl {
    gitRepository(URI("https://github.com/seoly0/SpringUtils.git")) {
        producesModule("me.seoly.spring:utils")
    }
}

sourceControl {
    gitRepository(URI("https://github.com/seoly0/JCommonUtils.git")) {
        producesModule("me.seoly.utils:common")
    }
}