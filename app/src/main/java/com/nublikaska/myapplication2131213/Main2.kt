package com.nublikaska.myapplication2131213

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.diff.DiffEntry
import org.eclipse.jgit.lib.ObjectId
import org.eclipse.jgit.treewalk.CanonicalTreeParser
import java.io.File

fun main() {


    val projectPath = File("").absolutePath

    val git: Git = Git.open(File(projectPath))

    val diff = git.getDiffLastCommit()

    val affectedModules = diff.mapTo(HashSet()) { it.substringBefore('/') }
}

private fun Git.getDiffLastCommit(): List<String> {

    val head: ObjectId = repository.resolve("HEAD~1^{tree}" )!!
    val lastCommit = CanonicalTreeParser()
    repository.newObjectReader().use { reader -> lastCommit.reset(reader, head) }

    return diff().setOldTree(lastCommit).call().map { it.newPath }
}