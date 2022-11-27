#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#define FALSE 0
#define TRUE !FALSE

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

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
void* peekFirst(LinkedList* list);
void* peekLast(LinkedList* list);

typedef void (*listFunc)(void* data);
void printLinkedList(LinkedList* list, listFunc funcPtr);
void freeLinkedList(LinkedList* list, listFunc funcPtr);

#endif
