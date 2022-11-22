package moe.yiyu.obsidian_tool.vault

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path

fun getGraphNodesCollection(documents: List<Document>): List<VaultGraphNode> {
    val map = getIndexMap(documents)
    val reverseMap = getReverseIndexMap(documents, map)
    val graphNodesList = mutableListOf<VaultGraphNode>()
    map.forEach{ (k,v) ->
        run {
            val title = k.title
            val outLinks = v.toList()
            val inLinks = reverseMap[k]!!.toList()
            val tmp = VaultGraphNode(title, outLinks, inLinks)
            graphNodesList.add(tmp)
        }
    }
    return graphNodesList
}

/**
 * 格式为：{文件名: [文件名, 文件名, ...]}
 * 列表中是该Document内包含的所有链接
 */
fun getIndexMap(documents: List<Document>): MutableMap<Document, List<String?>> {
    val map: MutableMap<Document, List<String?>> = mutableMapOf()
    documents.forEach { document ->
        val backlinks = document.backLinks?.map { it.link }
        map[document] = backlinks ?: listOf()
    }
    return map
}

/**
 * 格式为：{文件名: [文件名, 文件名, ...]}
 * 列表中是所有指向该Document的页面的文件名
 */
fun getReverseIndexMap(documents: List<Document>, map:Map<Document, List<String?>>): MutableMap<Document, List<String>> {
    val documentsAndDocsPointToIt: MutableMap<Document, List<String>> = mutableMapOf()
    documents.forEach { document ->
        val name = document.title
        val pointsToMe = map.filter { it.value.contains(name) }.map { it.key.title }
        documentsAndDocsPointToIt[document] = pointsToMe
    }
    return documentsAndDocsPointToIt
}

fun vaultPathResolver(vaultFolderName: String): MutableList<VaultFile> {
    val rootPath = Path(System.getProperty("user.dir"))
    val vaultPath: Path = rootPath.resolve(vaultFolderName)
    val vaultFiles: MutableList<VaultFile> = mutableListOf()
    Files.walk(vaultPath).forEach {
        val chosenFile = it.toFile()
        if (chosenFile.name.endsWith(".md")) {
            vaultFiles.add(MarkdownFile(chosenFile))
        } else {
            if (!chosenFile.isDirectory) {
                vaultFiles.add(NonMarkdownFile(chosenFile))
            }
        }
    }
    return vaultFiles
}

fun renderDocumentToHTML(doc:Document){
    val html = doc.content
//    Output to "tmp" directory
    val tmpPath = Path(System.getProperty("user.dir")).resolve("tmp")
//    the html file is under the tmp Path, name with doc title
    val htmlFile = tmpPath.resolve(doc.title+".html").toFile()
//    TODO:添加真正的Markdown到HTML的渲染器，还要转换路径
    htmlFile.writeText(html)
}