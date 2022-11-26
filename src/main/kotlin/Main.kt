import moe.yiyu.obsidian_tool.vault.*
import org.jgrapht.graph.builder.GraphBuilder

fun main(args: Array<String>) {
//    val files = vaultPathResolver("knowledge")
    val files = vaultPathResolver("02000s/02000")
    val documents = getAllDocuments(files)
    var total_links = 0
    val backlinks = ArrayList<String>()
    documents.forEach { document ->
    total_links += document.backLinks?.size ?: 0
        document.backLinks?.forEach{
            backlinks.add(it.rawLink)
        }
    }
    println("Total links: $total_links")
    println("Total Unique Links " + backlinks.distinct().size)
}

fun getIndex(documents: List<Document>): MutableMap<Document, List<String?>> {
    val map: MutableMap<Document, List<String?>> = mutableMapOf()
    documents.forEach { document ->
        var name = document.title
        val backlinks = document.backLinks?.map { it.link }
        map[document] = backlinks ?: listOf()
    }
    return map
}

fun getReverseIndex(documents: List<Document>, map: Map<Document, List<String?>>): MutableMap<Document, List<String>> {
    val documentsAndDocsPointToIt: MutableMap<Document, List<String>> = mutableMapOf()
    documents.forEach { document ->
        val name = document.title
        val pointsToMe = map.filter { it.value.contains(name) }.map { it.key.title }
//        println("$name has ${pointsToMe.size} point to it")
        documentsAndDocsPointToIt[document] = pointsToMe
    }
    return documentsAndDocsPointToIt
}


fun getAllDocuments(files: List<VaultFile>): List<Document> {
    val mdFiles = files.filterIsInstance<MarkdownFile>()
    return mdFiles.map { it.toDocument() }
}