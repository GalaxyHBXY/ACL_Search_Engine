import json
import re


def main(file_path):
    lines = None
    with open(file_path, 'r') as file:
        lines = file.readlines()

    clean_data = []
    paper = ""
    for line in lines:
        if line.startswith("@"):
            if paper == "":
                continue

            paper_dict = {}
            # find key value pairs for each paper
            for key, value in re.findall(r'(\w+)\s*=\s*("[^"]+"|\w+|{[^{}]+}),?', paper):
                if key.lower() in ["title", "abstract", "year", "url"]:
                    # remove special symbols and multiple white space
                    value = re.sub(r'[\n{}$\\",]', "", value.strip())
                    value = re.sub(r'\s+', " ", value)
                    paper_dict[key.strip()] = value

            paper = ""
            clean_data.append(paper_dict)

            continue
        paper += line

    with open("src/main/resources/data.json", 'w') as file:
        file.write(json.dumps([d for d in clean_data]))


if __name__ == '__main__':
    main("src/main/resources/anthology.bib")
