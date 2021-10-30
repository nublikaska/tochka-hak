package com.nublikaska.myapplication2131213

import org.eclipse.jgit.api.Git
import java.io.File

fun main() {

    val projectPath = File("").absolutePath

    val git: Git = Git.open(File(projectPath))

    val status = git.diff().call()

    val s = ""
}