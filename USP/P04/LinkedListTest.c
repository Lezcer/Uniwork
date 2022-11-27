/*
 * File: LinkedListTest.c
 * File Created: Thursday, 2nd April 2020 5:54:10 pm
 * Author: Jonathon Winter
 * -----
 * Last Modified: Friday, 3rd April 2020 2:14:40 am
 * Modified By: Jonathon Winter
 * -----
 * Purpose: A Test harness for a Linked List in 'C'. You are not needed to understand how it all works. 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>

#include "LinkedList.h"

int counter = 0;

/* DO NOT CHANGE ANY OF THESE DEFINES */
   #define FALSE 0
   #define TRUE !FALSE
   #define RED "\033[0;31m"
   #define GREEN "\033[0;32m"
   #define RESET "\033[0m"

   #define STR_MATCH(a,b) (strncmp((a),(b),strlen(b)+1) == 0)

   #define TEST_COUNT 8

   #define STR1 "Hello"
   #define STR2 "World"
   #define STR3 "Steve"
   #define STR4 "Bob"
   #define STR5 "Alice"
/* END OF THAT BLOCK */

/* Change these if you have named your Linked Lists structs something weird :P */
   #define LIST LinkedList
   #define NODE ListNode
   #define HEAD head
   #define TAIL tail
   #define DATA data
   #define COUNT count
/* END OF THAT BLOCK*/

/* Uncomment this define if you did a double ended array and want to test that */
   /* #define DOUBLE_ENDED */
   /* #define DOUBLE_LINKED */
/* END OF THAT BLOCK*/

static char* createString(char* input);
static void printTest(char* input);
static void printFailure(char* format, ...);
void printString(void* data);
void freeString(void* data);

typedef int(*testFunc)(LIST* list);

int checkHead(LIST* list, char* input)
{  
   int success = FALSE;
   if(list->HEAD == NULL)
   {
      printFailure("Head is still NULL");
   }
   else if(!STR_MATCH((char*)(list->HEAD->DATA), input))
   {
      printFailure("Head data %s != %s", (char*)(list->HEAD->DATA), input);
   }
   else
   {
      success = TRUE;
      #ifdef DOUBLE_LINKED
         success = success && (list->HEAD->prev == NULL);
         if(!success)
         {
            printFailure("head->prev is not NULL");
         }
      #endif

   }
   
   return success;
}

int checkTail(LIST* list, char* input)
{
   int success = FALSE;

   #ifdef DOUBLE_ENDED
      if(list->TAIL == NULL)
      {
         printFailure("Tail is still NULL");
      }
      else if(!STR_MATCH((char*)(list->TAIL->DATA), input))
      {
         printFailure("Tail data %s != %s", (char*)(list->TAIL->DATA), input);
      }
      else
      {
         success = TRUE;
         #ifdef DOUBLE_LINKED
            success = success && (list->TAIL->next == NULL);
            if(!success)
            {
               printFailure("tail->next is not NULL");
            }
         #endif
      }
   #else
      NODE* current = list->HEAD;
      while(current->next != NULL)
      {
         current = current->next;
      }
      
      if(!STR_MATCH((char*)(current->DATA), input))
      {
         printFailure("Tail data %s != %s", (char*)(current->DATA), input);
      }
      else if(current->next != NULL)
      {
         printFailure("tail->next is not NULL");
      }
      else
      {
         success = TRUE;
      }
   #endif


   return success;
}

int checkCount(LIST* list, int count)
{
   int success = TRUE;

   if(list->count != count)
   {
      success = FALSE;
      printFailure("List Couint (%d) != %d", list->count, count);
   }

   return success;
}

LIST* testCreation()
{
   LIST* list = NULL;

   printTest("Creating List");
   list = createLinkedList();

   if(list == NULL)
   {
      printFailure("List == NULL");
      list = NULL;
   }
   else if (list->HEAD != NULL)
   {
      printFailure("List head != NULL");
      list = NULL;
   }
   #ifdef DOUBLE_ENDED
   else if (list->TAIL != NULL)
   {
      printFailure("List tail != NULL");
      list = NULL;
   }
   #endif
   else if (list->count != 0)
   {
      printFailure("List count (%d) != %d", list->count, 0);
      list = NULL;
   }

   return list;
}

int testInsertStart1(LIST* list)
{
   int success = FALSE;
   char* str = createString(STR2);

   printTest("Insert First #1");
   insertStart(list, str);

   success = checkHead(list, str) && 
               checkTail(list, str) &&
               checkCount(list,1);
   
   return success;
}

int testInsertStart2(LIST* list)
{
   int success = FALSE;
   char* str = createString(STR1);

   printTest("Insert First #2");
   insertStart(list, str);

   success = checkHead(list, str) && 
               checkTail(list, STR3) &&
               checkCount(list, 3);

   return success;
}

int testInsertStart3(LIST* list)
{
   int success = FALSE;
   char* str = createString(STR5);

   printTest("Insert First #3");
   insertStart(list, str);

   success = checkHead(list, str) && 
               checkTail(list, STR3) &&
               checkCount(list, 3);

   return success;
}

int testInsertLast1(LIST* list)
{
   int success = FALSE;
   char* str = createString(STR3);

   printTest("Insert Last #1");

   insertLast(list, str);

   success = checkTail(list, str) && 
               checkCount(list, 2);

   return success;
}

int testInsertLast2(LIST* list)
{
   int success = FALSE;
   char* str = createString(STR4);

   printTest("Insert Last #2");

   insertLast(list, str);

   success = checkTail(list, str) && 
               checkCount(list, 4);

   return success;
}

int testRemoveStart(LIST* list)
{
   int success;
   char* removed;
   
   printTest("Remove First");

   removed = removeStart(list);

   success = (strncmp(removed, STR1, strlen(STR1)+1) == 0) && 
               checkHead(list, STR2) && 
               checkCount(list, 3);

   /* Only success if both checks are passed */
   free(removed);

   return success ;
}

int testRemoveLast(LIST* list)
{
   int success;

   char* removed;
   printTest("Remove Last");

   removed = removeLast(list);

   success = (strncmp(removed, STR4, strlen(STR4)+1) == 0) && 
               checkTail(list, STR3) && 
               checkCount(list, 2);

   free(removed);

   return success ;
}

void testPrintList(LIST* list)
{
   printf(" Count: %d\n", list->COUNT);
   printLinkedList(list, &printString);
}

int testFreeList(LIST* list)
{
   printTest("Free List");
    printf("%d\n",list->count);
   freeLinkedList(list, &freeString);
   /* There isnt really a way to test if it properly freed (Use valgrind) */
   return TRUE;
}

static void printTest(char* input)
{
   printf("%-16s: ", input);
}

static void printFailure(char* format, ...)
{
   va_list args;
   va_start(args, format);

   printf(RED);
   vprintf(format, args);
   printf("\n"RESET);

   va_end(args);
}

void printString(void* data)
{
   char* str = (char*)data;
   printf("  %s\n", str);
}

void freeString(void* data)
{
   counter--;
   printf("%d\n", counter);
   free(data);
}

static char* createString(char* input)
{
   int length = strlen(input) + 1;


   char* str = (char*)malloc(sizeof(char) * length);
   strncpy(str,input, length);
   counter++;
   printf("%d\n", counter);

   return str;
}

int main(int argc, char const *argv[])
{
   LIST* list = NULL;
   char* color = RED;
   int ii;
   int passCount = 0;
   int success = TRUE;

   testFunc currentTest = NULL;

   testFunc tests[TEST_COUNT] = {
      &testInsertStart1,
      &testInsertLast1,
      &testInsertStart2,
      &testInsertLast2,
      &testRemoveStart,
      &testRemoveLast,
      &testInsertStart3,
      &testFreeList,
   };

   list = testCreation();

   if(list != NULL)
   {  
      printf(GREEN "PASSED\n" RESET );
      ii = 0;
      passCount++;
      while(ii < TEST_COUNT && success == TRUE)
      {  
         currentTest = tests[ii];
         success = (*currentTest)(list);

         if(success)
         {
            printf(GREEN "PASSED\n" RESET );
            passCount++;
         }
         else
         {
            printf("Here is what your list looked like at time of fail\n");
            testPrintList(list);
         }
         ii++;
         
      }
   }
   
   if (passCount == TEST_COUNT+1)
   {
      color = GREEN;
   }

   printf("\nTest passed: %s %d/%d\n\n" RESET ,color, passCount, TEST_COUNT+1);

   return 0;
}
