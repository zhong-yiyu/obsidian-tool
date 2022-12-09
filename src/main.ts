import { writeFile } from "fs/promises";
import { crawl_dir, getDoubleLinkInFile } from "./util/files.js";

let files = await crawl_dir("knowledge");

files.forEach(async (file) => {
  let links = await getDoubleLinkInFile(file);
});

// Create a dictionary for each file, the "filename" key has the value of file name, and the "links" key has the value of an array of links.
let files_dict = files.map(async (file) => {
  let links = await getDoubleLinkInFile(file);
  // If links is empty, return null.
  if (links.length != 0) {
    return {
      filename: file.split("/").pop()?.split(".")[0],
      link: links,
    };
  }
});
let tmp = await Promise.all(files_dict);
// REmove all null values.
tmp = tmp.filter((item) => item !== undefined);
// Write the dictionary to a json file, format the json file.
writeFile("links.json", JSON.stringify(tmp, null, 4), "utf-8");