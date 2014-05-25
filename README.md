The Project [WIP]
===================

This is the readme. Please read before doing anything.

OKAY! So, what we should get started on first is making actor classes in an effort to convert the mob superclass from DM code and change it into Java.

The mob functions as the basic format for all npcs and players in the game. The player is a mob that is controlled by current input system. (an assortment of GUI buttons and whatnot) The npcs are controlled by code.

SO! How are we going to do this?

We FIRST need to do the GUI so we can see what the heck we're doing!

The first two things that need to be implemented are the frames and the output text box.

Once we got that down...

We need to define the following:

the constructor needs to initialize ALL STATS 

statList = new
		statList += list(level,hp,ele,shield,stam,cons,agil,str,fin,apt,res,sp,defVal,atkVal)
		hidList = new //a hidden list to show all stats, including hidden ones
		hidList += list(level, hp, ele, shield, stam, cons, agil, str, fin, apt, res, sp, armorVal, weaponVal, weaponRange, defVal, atkVal, momentum, critChance, critDamage)

ALL OF THEM!

The constructor must also initialize a name for each npc. That's not hard.

Then, this is the rather difficult part.

These npc/players need to be able to equip things.

so, it should have a private list that contains all forms of weapons and armor equipped. It also needs a set of methods that manage these items to and fro the inventory list, which should also be initialized.

Items should look like this in terms of class hierarchy:

obj --> item --> gear -- > weapon

obj --> item --> gear -- > armor

So! This is our first hurdle! The original actors, as well as the original objects that the actors can equip.

Let's get to it people!


How to Run and Compile
======================
There are many ways to get this onto your computer. When things get complicated later on with more files and folders, I suggest downloading the zip by pressing the button on the right side of the page and opening it in an IDE. I will be pushing the files from an IDE named IntelliJ so that will be required if you want to open the project directly. Otherwise, you can just copy and paste code onto your computer and compile it whichever way you want.
