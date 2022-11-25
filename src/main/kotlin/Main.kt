import moe.yiyu.obsidian_tool.vault.*

fun main(args: Array<String>) {
    val files = vaultPathResolver("knowledge")
    val documents = getAllDocuments(files)
    val map = getIndexMap(documents)
    getReverseIndex(documents, map)
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

fun getReverseIndex(documents: List<Document>, map: Map<Document, List<String?>>) {
    val documentsAndDocsPointToIt: MutableMap<Document, List<String>> = mutableMapOf()
    documents.forEach { document ->
        val name = document.title
        val pointsToMe = map.filter { it.value.contains(name) }.map { it.key.title }
        println("$name has ${pointsToMe.size} point to it")
        documentsAndDocsPointToIt[document] = pointsToMe
    }
}


fun getAllDocuments(files: List<VaultFile>): List<Document> {
    val mdFiles = files.filterIsInstance<MarkdownFile>()
    return mdFiles.map { it.toDocument() }
}