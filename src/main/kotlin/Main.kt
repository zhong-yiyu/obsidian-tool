import moe.yiyu.obsidian_tool.vault.*
import java.nio.file.Files
import java.nio.file.Path
import javax.print.Doc
import kotlin.io.path.Path

fun main(args: Array<String>) {
    val files = vaultPathResolver("knowledge")
//    Filter out classes that are not MarkdownFile
    val mdFiles = files.filterIsInstance<MarkdownFile>()
    var nonMdFiles = files.filterIsInstance<NonMarkdownFile>()
    var documents = mdFiles.map { it.toDocument() }
//    Create a string to string empty map
    var map: MutableMap<Document, List<String?>> = mutableMapOf()

    documents.forEach { document ->
        var name = document.title
        var backlinks = document.backLinks?.map { it.link }
        map[document] = backlinks ?: listOf()
    }

    var docmentsAndDocsPointToIt:MutableMap<Document, List<String>> = mutableMapOf()

    documents.forEach { document ->
        var name = document.title
        var pointsToMe = map.filter { it.value.contains(name) }.map { it.key.title }
        println("$name has ${pointsToMe?.size} point to it")
        docmentsAndDocsPointToIt[document] = pointsToMe
    }
}

fun vaultPathResolver(vaultFolderName: String): MutableList<VaultFile> {
    var rootPath: Path = Path(System.getProperty("user.dir"))
    var vaultPath: Path = rootPath.resolve(vaultFolderName)
    var vaultFiles: MutableList<VaultFile> = mutableListOf()
    Files.walk(vaultPath).forEach {
        var chosenFile = it.toFile()
        if (chosenFile.name.endsWith(".md")) {
            vaultFiles.add(MarkdownFile(chosenFile))
        } else {
            if (chosenFile.isDirectory) {

            } else {
                vaultFiles.add(NonMarkdownFile(chosenFile))
            }
        }
    }
    return vaultFiles
}