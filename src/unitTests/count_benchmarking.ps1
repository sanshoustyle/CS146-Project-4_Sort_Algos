# /--------- PowerShell script to track performance ------------/

# This powershell script tracks the run times different sorting algorithms

# usage: run from src directory

# In order the texts are: 
# Shakespeare: hamlet, clarissa, the-tempest
# Sir Francis Bacon: essays, soled, the-new-atlantis
# King James Bible
# alice (Alice in Wonderland)

# Tests in order are (is, qs, ms)

# Written by Thien Van, Elton Vinh
# for CS 146 Section 01-Shaverdian Fall 2015
# Project 4 Benchmarking


# /------- Java BST ------------

# 1 Get Start Time
$startDTMi1=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -is .\txtFiles\hamlet.txt

# Get End Time
$endDTMi1=(Get-Date)

# 2 Get Start Time
$startDTMi2=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -is .\txtFiles\clarissa.txt

# Get End Time
$endDTMi2=(Get-Date)

# 3 Get Start Time
$startDTMi3=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -is .\txtFiles\the-tempest.txt

# Get End Time
$endDTMi3=(Get-Date)

# 4 Get Start Time
$startDTMi4=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -is .\txtFiles\essays.txt

# Get End Time
$endDTMi4=(Get-Date)

# 5 Get Start Time
$startDTMi5=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -is .\txtFiles\soled.txt

# Get End Time
$endDTMi5=(Get-Date)

# 6 Get Start Time
$startDTMi6=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -is .\txtFiles\the-new-atlantis.txt

# Get End Time
$endDTMi6=(Get-Date)

# 7 Get Start Time
$startDTMi7=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -is .\txtFiles\king-james-bible.txt

# Get End Time
$endDTMi7=(Get-Date)

# 8 Get Start Time
$startDTMi8=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -is .\txtFiles\alice.txt

# Get End Time
$endDTMi8=(Get-Date)

# ///////////////////////////////////////////////////////////////

# quickSort

# 1 Get Start Time
$startDTMq1=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -qs .\txtFiles\hamlet.txt

# Get End Time
$endDTMq1=(Get-Date)

# 2 Get Start Time
$startDTMq2=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -qs .\txtFiles\clarissa.txt

# Get End Time
$endDTMq2=(Get-Date)

# 3 Get Start Time
$startDTMq3=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -qs .\txtFiles\the-tempest.txt

# Get End Time
$endDTMq3=(Get-Date)

# 4 Get Start Time
$startDTMq4=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -qs .\txtFiles\essays.txt

# Get End Time
$endDTMq4=(Get-Date)

# 5 Get Start Time
$startDTMq5=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -qs .\txtFiles\soled.txt

# Get End Time
$endDTMq5=(Get-Date)

# 6 Get Start Time
$startDTMq6=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -qs .\txtFiles\the-new-atlantis.txt

# Get End Time
$endDTMq6=(Get-Date)

# 7 Get Start Time
$startDTMq7=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -qs .\txtFiles\king-james-bible.txt

# Get End Time
$endDTMq7=(Get-Date)

# 8 Get Start Time
$startDTMq8=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -qs .\txtFiles\alice.txt

# Get End Time
$endDTMq8=(Get-Date)

# ///////////////////////////////////////////////////////////////

# mergeSort

# 1 Get Start Time
$startDTMm1=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -ms .\txtFiles\hamlet.txt

# Get End Time
$endDTMm1=(Get-Date)

# 2 Get Start Time
$startDTMm2=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -ms .\txtFiles\clarissa.txt

# Get End Time
$endDTMm2=(Get-Date)

# 3 Get Start Time
$startDTMm3=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -ms .\txtFiles\the-tempest.txt

# Get End Time
$endDTMm3=(Get-Date)

# 4 Get Start Time
$startDTMm4=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -ms .\txtFiles\essays.txt

# Get End Time
$endDTMm4=(Get-Date)

# 5 Get Start Time
$startDTMm5=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -ms .\txtFiles\soled.txt

# Get End Time
$endDTMm5=(Get-Date)

# 6 Get Start Time
$startDTMm6=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -ms .\txtFiles\the-new-atlantis.txt

# Get End Time
$endDTMm6=(Get-Date)

# 7 Get Start Time
$startDTMm7=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -ms .\txtFiles\king-james-bible.txt

# Get End Time
$endDTMm7=(Get-Date)

# 8 Get Start Time
$startDTMm8=(Get-Date)

# Run script
java -cp . wordcounter.WordCount -h -ms .\txtFiles\alice.txt

# Get End Time
$endDTMm8=(Get-Date)

# Echo Time elapsed - Java-HashTable InsertionSort
"Elapsed Time Hamlet Java-HashTable InsertionSort: $(($endDTMi1-$startDTMi1).totalseconds) seconds"
"Elapsed Time Clarissa Java-HashTable InsertionSort: $(($endDTMi2-$startDTMi2).totalseconds) seconds"
"Elapsed Time The-Tempest Java-HashTable InsertionSort: $(($endDTMi3-$startDTMi3).totalseconds) seconds"
"Elapsed Time Essays Java-HashTable InsertionSort: $(($endDTMi4-$startDTMi4).totalseconds) seconds"
"Elapsed Time Soled Java-HashTable InsertionSort: $(($endDTMi5-$startDTMi5).totalseconds) seconds"
"Elapsed Time The-New-Atlantis Java-HashTable InsertionSort: $(($endDTMi6-$startDTMi6).totalseconds) seconds"
"Elapsed Time King-James-Bible Java-HashTable InsertionSort: $(($endDTMi7-$startDTMi7).totalseconds) seconds"
"Elapsed Time Alice Java-HashTable InsertionSort: $(($endDTMi8-$startDTMi8).totalseconds) seconds"

""

# Echo Time elapsed - Java-HashTable QuickSort
"Elapsed Time Hamlet Java-HashTable QuickSort: $(($endDTMq1-$startDTMq1).totalseconds) seconds"
"Elapsed Time Clarissa Java-HashTable QuickSort: $(($endDTMq2-$startDTMq2).totalseconds) seconds"
"Elapsed Time The-Tempest Java-HashTable QuickSort: $(($endDTMq3-$startDTMq3).totalseconds) seconds"
"Elapsed Time Essays Java-HashTable QuickSort: $(($endDTMq4-$startDTMq4).totalseconds) seconds"
"Elapsed Time Soled Java-HashTable QuickSort: $(($endDTMq5-$startDTMq5).totalseconds) seconds"
"Elapsed Time The-New-Atlantis Java-HashTable QuickSort: $(($endDTMq6-$startDTMq6).totalseconds) seconds"
"Elapsed Time King-James-Bible Java-HashTable QuickSort: $(($endDTMq7-$startDTMq7).totalseconds) seconds"
"Elapsed Time Alice Java-HashTable QuickSort: $(($endDTMq8-$startDTMq8).totalseconds) seconds"

""

# Echo Time elapsed - Java-HashTable MergeSort
"Elapsed Time Hamlet Java-HashTable MergeSort: $(($endDTMm1-$startDTMm1).totalseconds) seconds"
"Elapsed Time Clarissa Java-HashTable MergeSort: $(($endDTMm2-$startDTMm2).totalseconds) seconds"
"Elapsed Time The-Tempest Java-HashTable MergeSort: $(($endDTMm3-$startDTMm3).totalseconds) seconds"
"Elapsed Time Essays Java-HashTable MergeSort: $(($endDTMm4-$startDTMm4).totalseconds) seconds"
"Elapsed Time Soled Java-HashTable MergeSort: $(($endDTMm5-$startDTMm5).totalseconds) seconds"
"Elapsed Time The-New-Atlantis Java-HashTable MergeSort: $(($endDTMm6-$startDTMm6).totalseconds) seconds"
"Elapsed Time King-James-Bible Java-HashTable MergeSort: $(($endDTMm7-$startDTMm7).totalseconds) seconds"
"Elapsed Time Alice Java-HashTable MergeSort: $(($endDTMm8-$startDTMm8).totalseconds) seconds"

""

