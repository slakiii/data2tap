# data2tap

This is a small program I created to load the text input file of AoC 2024, day 1 into my ZX Spectrum.
The code generates a _tap_ file which can be converted into a _wav_ file.

## Usage

    mkdir output
    clojure -M -m aoc.puzzle-input resources/year2024_day01.txt output/data.tap

Then you can use _tape2wav_ in Linux to get the _wav_ file:

    tape2wav output/data.tap output/data.wav
