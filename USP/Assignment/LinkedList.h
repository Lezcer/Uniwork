#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define FALSE 0
#define TRUE !FALSE

/*#include "Alloc.h"*/

typedef struct ListNode {
    void* data;
    struct ListNode* next;
    } ListNode;

typedef struct {
    ListNode* head;
    ListNode* tail;
    int count;
    } LinkedList;

int isEmpty(LinkedList* list);
LinkedList* createLinkedList();
void insertStart(LinkedList* list, void* entry);
void* removeStart(LinkedList* list);
void insertLast(LinkedList* list, void* entry);
void* removeLast(LinkedList* list);


typedef void (*listFunc)(void* data);
void printLinkedList(LinkedList* list, listFunc funcPtr);
void freeLinkedList(LinkedList* list, listFunc funcPtr);

#endif
