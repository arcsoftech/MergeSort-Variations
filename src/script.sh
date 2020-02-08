#!/bin/sh

 javac -d bin arc180006/*.java 
 cd bin
 java arc180006.MergeSort 1600 1 >> ../output.txt 
 java arc180006.MergeSort 1600 2 >> ../output.txt 
 java arc180006.MergeSort 1600 3 >> ../output.txt 
 java arc180006.MergeSort 1600 4 >> ../output.txt 
 java arc180006.MergeSort 3200 1 >> ../output.txt 
 java arc180006.MergeSort 3200 2 >> ../output.txt 
 java arc180006.MergeSort 3200 3 >> ../output.txt 
 java arc180006.MergeSort 3200 4 >> ../output.txt 
 java arc180006.MergeSort 6400 1 >> ../output.txt 
 java arc180006.MergeSort 6400 2 >> ../output.txt 
 java arc180006.MergeSort 6400 3 >> ../output.txt 
 java arc180006.MergeSort 6400 4 >> ../output.txt 
 java arc180006.MergeSort 12800 1 >> ../output.txt 
 java arc180006.MergeSort 12800 2 >> ../output.txt 
 java arc180006.MergeSort 12800 3 >> ../output.txt 
 java arc180006.MergeSort 12800 4 >> ../output.txt 
 