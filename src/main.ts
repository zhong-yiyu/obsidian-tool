import { writeFile } from "fs/promises";
import { IndentStyle } from "typescript";
import { crawl_dir, getDoubleLinkInFile } from "./util/files.js";

let files = await crawl_dir("knowledge");

files.forEach(async (file) => {
    let links = await getDoubleLinkInFile(file);
});

// Create a dictionary for each file, the "filename" key has the value of file name, and the "links" key has the value of an array of links.
let files_dict = files.map(async (file) => {
    let links = await getDoubleLinkInFile(file);
    return {
        filename: file,
        link: links,
    };
}
);
let tmp = await Promise.all(files_dict);
// Write the dictionary to a json file, format the json file.
writeFile("links.json", JSON.stringify(tmp, null, 4), "utf-8");

// Write the dictionary to a json file.
// writeFile("links.json", JSON.stringify(files_dict), "utf-8");