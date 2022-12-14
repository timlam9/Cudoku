package com.lamti.cudoku.domain

const val emptyInitialGrid = ",,,,,,,,\n" +
        ",,,,,,,,\n" +
        ",,,,,,,,\n" +
        ",,,,,,,,\n" +
        ",,,,,,,,\n" +
        ",,,,,,,,\n" +
        ",,,,,,,,\n" +
        ",,,,,,,,\n" +
        ",,,,,,,,"

const val initialGrid = "5,3,,,7,,,,\n" +
        "6,,,1,9,5,,,\n" +
        ",9,8,,,,,6,\n" +
        "8,,,,6,,,,3\n" +
        "4,,,8,,3,,,1\n" +
        "7,,,,2,,,,6\n" +
        ",6,,,,,2,8,\n" +
        ",,,4,1,9,,,5\n" +
        ",,,,8,,,7,9"

const val correctSolutionGrid = "5,3,4,6,7,8,9,1,2\n" +
        "6,7,2,1,9,5,3,4,8\n" +
        "1,9,8,3,4,2,5,6,7\n" +
        "8,5,9,7,6,1,4,2,3\n" +
        "4,2,6,8,5,3,7,9,1\n" +
        "7,1,3,9,2,4,8,5,6\n" +
        "9,6,1,5,3,7,2,8,4\n" +
        "2,8,7,4,1,9,6,3,5\n" +
        "3,4,5,2,8,6,1,7,9"

const val anotherCorrectSolutionGrid = "3,1,6,5,7,8,4,9,2\n" +
        "5,2,9,1,3,4,7,6,8\n" +
        "4,8,7,6,2,9,5,3,1\n" +
        "2,6,3,4,1,5,9,8,7\n" +
        "9,7,4,8,6,3,1,2,5\n" +
        "8,5,1,7,9,2,6,4,3\n" +
        "1,3,8,9,4,7,2,5,6\n" +
        "6,9,2,3,5,1,8,7,4\n" +
        "7,4,5,2,8,6,3,1,9"
