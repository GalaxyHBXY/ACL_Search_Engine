# Introduction
This is an ACL search engine that allows you to filter papers based on different properties.

At this moment, the engine only supports AND search. That is, a paper will be considered valid if and only if it satisfies 
all property filters.

# How to use
This engine involves two subprojects:
1. BibTex Preprocess
2. JSON Search

BibTex Preprocess - invoked by `gradle preprocess`
1. Takes an ACL Anthology BibTex file (with abstracts) and represent each paper as a JSON object
2. Saves the JSON representation of the papers in `src/main/resources/data.json` 

JSON Search - invoked by `gradle run`
1. Loads application window
2. User chooses property filters
3. Saves filtered papers in `result.html`

# Contact
zewei.shi@sydney.edu.au

yidong.gan@sydney.edu.au
