#include "main.h"

int main(int argc, char** argv)
{
    if(argc == 4)
    {
        Character* hero; 
        Character* enemy;
        LinkedList* enemies;
        float sleepTime;
        int INITIAL_HP, HEAL_HP;/*initial HP of hero AND healing HP*/
        int damage;/*damage number*/
        int drop;/*drop case*/
        int crit;/*basically a boolean, if its a crit*/
    
        hero = readHero(argv[1]);
        INITIAL_HP = hero->hp;
        HEAL_HP = (int)(((double) INITIAL_HP) * 0.3);
        enemies = readEnemies(argv[2]);
        sleepTime = atof(argv[3]);
    
        system("clear"); 
        newSleep(sleepTime);
        welcome();
    
        newSleep(sleepTime);    
        system("clear"); 
        gameStatus(hero, enemies);

        enemy = (Character*)removeLast(enemies);

        while(!isEmpty(enemies) || hero->hp != 0)
        {
            newSleep(sleepTime);    
            system("clear"); 
            gameStatus(hero, enemies);
            /*Hero 
            attack*/
            newSleep(sleepTime);    
            system("clear"); 
            crit = randomCrit();
            damage = enemy->atk - hero->def;
            if(crit)
            {
                damage = (enemy->atk * 2) - hero->def;
            }/*Over write*/
            enemy->hp = enemy->hp - damage;

            heroStatus(hero);
            damage = hero->atk - enemy->def;
            if(hero->hp <= HEAL_HP && hero->hp != 0 && hero->med > 0)
            {
                hero->med = hero->med - 1;
                hero->hp = INITIAL_HP;
                healing(hero->name);
            }
            newSleep(sleepTime);    
            damageStatus(hero->name,enemy->name, damage, crit);
            newSleep(sleepTime);    
            enemyStatus(enemy);

            if(enemy->hp==0 && !isEmpty(enemies))
            {
                /*remove a new enemy*/
                enemy = (Character*)removeLast(enemies);
                /*hero drop*/
                drop = randomReward();
                switch(drop)
                {
                    case '1':
                        hero->atk = (int)(((double) hero->atk )* 1.5);/*Better Sword*/
                        newSleep(sleepTime);    
                        printf("%s got a better sword! ATK is now %d", hero->name, hero->atk);
                    break;
                    case '0':
                        hero->def = (int)(((double) hero->def )* 1.5);/*Better Armor*/
                        newSleep(sleepTime);    
                        printf("%s got a better sheild! DEF is now %d", hero->name, hero->def);
                    break;
                    case -1:
                        hero->med = hero->med + 1;/*1 Medicine*/
                        newSleep(sleepTime);    
                        printf("%s found a medicine drop!", hero->name);
                    break;
                }
            }
            
            /*Enemy 
            attack*/
            if(!isEmpty(enemies))
            {
                crit = randomCrit();
                damage = enemy->atk - hero->def;
                if(crit)
                {
                    damage = (enemy->atk * 2) - hero->def;
                }/*Over write*/
                hero->hp = hero->hp - damage;

                newSleep(sleepTime);    
                heroStatus(hero);
                newSleep(sleepTime);    
                damageStatus(enemy->name,hero->name, damage, crit);
                newSleep(sleepTime);    
                enemyStatus(enemy);
            }

        }
        if(!isEmpty(enemies))
        {
            system("clear"); 
            win(TRUE, hero->name);
        }
        else
        {
            system("clear"); 
            win(FALSE, hero->name);
        }
        
    }
    else
    {
        printf("Usage: ./rpg hero.txt enemy.txt <sleep time>");
    }
    return 0;
}
int randomCrit(void)
{
   return FALSE; 
}
int randomReward(void)
{
   return 1; 
}
