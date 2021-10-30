import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.ObjectId
import org.eclipse.jgit.treewalk.CanonicalTreeParser
import java.io.File

object Library {

    fun getAffectedModules(project: File): Set<String> {

        //println("UUUUUUUU" + project.absolutePath)

        val git: Git = Git.open(project)

        val diff = git.getDiffLastCommit()

        return diff.mapTo(HashSet()) { it.substringBefore('/') }
    }

    private fun Git.getDiffLastCommit(): List<String> {

        val head: ObjectId = repository.resolve("HEAD~1^{tree}" )!!
        val lastCommit = CanonicalTreeParser()
        repository.newObjectReader().use { reader -> lastCommit.reset(reader, head) }

        return diff().setOldTree(lastCommit).call().map { it.newPath }
    }
}