import { PathLike } from "fs";
import {readdir, readFile} from "fs/promises";

// Get all the markdown files in the directory, recursively.
// It's a async function, so we need to use await.
export async function crawl_dir(path: PathLike){
    let files: string[] = [];
    let dir = await readdir(path, {withFileTypes: true});
    for(let file of dir){
        if(file.isDirectory()){
            files = files.concat(await crawl_dir(`${path}/${file.name}`));
        }else if(file.isFile() && file.name.endsWith(".md")){
            files.push(`${path}/${file.name}`);
        }
    }
    return files;
}

export async function getDoubleLinkInFile(file: string){
    let content = await readFile(file, "utf-8");
    let links = content.match(/\[\[.*?\]\]/g);
    if(links){
        let out = links.map((link) => {
            return link.slice(2, -2);
        });
        return out;
    }
    return [];
}