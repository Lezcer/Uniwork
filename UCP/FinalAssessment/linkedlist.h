#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include <stdio.h>
#define FALSE 0
#define TRUE !FALSE
typedef struct LinkedListNode 
{
    void* data;
    struct LinkedListNode* next;
} LinkedListNode;

typedef struct
{
    LinkedListNode* head;
    LinkedListNode* tail;
    int count;
} LinkedList;

LinkedList* createLinkedList();
int getCount(LinkedList* list);
int isEmpty(LinkedList* list);
void insertStart(LinkedList* list, void* entry);
void* removeStart(LinkedList* list);
void insertLast(LinkedList* list, void* entry);
void* removeLast(LinkedList* list);
typedef void (*listFunc)(void* data);
void printLinkedList(LinkedList* list, listFunc funcPtr);
void freeLinkedList(LinkedList* list, listFunc funcPtr);
#endif
