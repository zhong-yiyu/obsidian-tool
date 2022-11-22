package moe.yiyu.obsidian_tool.vault
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph
import org.jgrapht.graph.builder.GraphBuilder


class VaultGraphHighPerformance:VaultGraph {
    var nodes: List<VaultGraphNode> = mutableListOf()
    var graph:Graph<VaultGraphNode, DefaultEdge> = SimpleGraph(DefaultEdge::class.java)
    fun addNode(node: VaultGraphNode) {
        nodes = nodes.plus(node)
        graph.addVertex(node)
    }
}