/*Name: Salah Mahamod
  Purpose: Contains functions to test Linked list functionality
  Notes for marker: call any function with the right parameters in the ipc.c main()
  Date: 23/05/2021
*/
#include "testharness.h"
void readFileTest(LinkedList* list)
{
    printLinkedList(list, &print);
}
void print(void* printThis)
{
    char** words;
    int k = 0;
    words = (char**)printThis;
    while(k != ARGNUM)
    {
        printf("%s ",words[k]);
        k++;
    }
    printf("\n");
}/*hard coded for ipc.c*/

void insertandRemoveTest(LinkedList* list)
{
    insertLast(list, "World!\n");
    insertStart(list, "Hello ");

    printf("%s%s", (char*)removeStart(list), (char*)removeLast(list));
}
