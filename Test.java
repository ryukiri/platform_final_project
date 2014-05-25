//progression goes here.
mob
	Login()
		..()
		spawnItem(/obj/item/gear/weapon/sword/rapier, src, null, 1)
		spawnItem(/obj/item/gear/weapon/sword/falchion, src, null, 1)
		spawnItem(/obj/item/gear/weapon/sword/longsword, src, null, 1)
		spawnSet(src, 1)
		Move(locate("Flashpoint"))
		var/mob/M = new()
		M.loc = locate("Flashpoint")
		M.name = "Faster"
		M.sp.perm(2)
		var/mob/X = new()
		X.loc = locate("Flashpoint")
		X.name = "Fast"
		X.sp.perm(1)
		var/area/A = locate("Flashpoint")
		var/mob/Z = new()
		Z.loc = locate("Flashpoint")
		Z.name = "Fastest"
		Z.sp.perm(4)
		new /battleAdmin(A)

var
	const
		single = 1
		double = 2
		range = 3

stat
	var
		value
		maxValue
		name
		mob/container
		vessel
		range
	New(newname, newvalue, user, rangeNumber, zeroed, booltype) //zeroed is a boolean meant to force a start at zero
		..()
		var/x
		if(!user)
			world << "[newname] deleted due to lacking a container"
			del src
		container = user
		if(newname)
			name = newname
		else
			newname = "Null Stat"
		if(newvalue)
			x = newvalue
		else
			x = 0
		if(rangeNumber)
			maxValue = rangeNumber

		value = x
		maxValue = x
		if(booltype == "vessel")
			vessel = 1
		if(booltype == "range")
			range = 1

	proc
		change(n)
			value = n
			maxValue = n

		maxChange(n)
			maxValue = n

		add(n)
			value += n
			if(value > maxValue)
				value = maxValue
			container.statUpdate()

		sub(n)
			value -= n
			if(value < 0)
				value = 0
			container.statUpdate()

		mult(n)
			value *= n
			if(value > maxValue)
				value = maxValue
			container.statUpdate()

		perm(n)
			value += n
			maxValue += n
			container.statUpdate()

		permChange(n)
			value = n
			maxValue = n
			container.statUpdate()

		display(n)
			if(n == 1)
				return "[value]"
			if(n == 2)
				return "[value]/[maxValue]"
			if(n == 3)
				return "[value] - [maxValue]"

		restore()
			value = maxValue

		toPercent()
			return "[round(100 * value / maxValue)]%"

mob
	var
		stat
			level
			hp
			ele
			shield
			stam
			cons
			agil
			str
			fin
			apt
			res
			sp
			armorVal
			weaponVal
			weaponRange
			defVal
			atkVal
			momentum
			critChance
			critDamage
			critChMod
			critDmgMod
			baseSp
		list
			debuffList
			statList
			hidList
			equipList
			inventory
	New()
		level = new("Level", 1, src)
		hp = new("Health", 50, src, null,null, "vessel")
		ele = new("Element", 20, src, null, null, "vessel")
		shield = new("Shield", 30, src, null, null, "vessel")
		stam = new("Stamina", 20, src, null, null, "vessel")
		cons = new("Constitution", 0, src)
		agil = new("Agility", 0,src)
		str = new("Strength", 1, src)
		fin = new("Finesse", 0,src)
		apt = new("Aptitude", 0,src)
		res = new("Resistance", 1 ,src)
		sp = new("Speed", 0,src)
		defVal = new("Defense Value", 0,src)
		atkVal = new("Attack Value", 3,src, null, null, "range")
		armorVal = new("Armor Value", 0,src)
		weaponVal = new("Weapon Value", 0,src)
		weaponRange = new("Weapon Range",0,src)
		momentum = new("Momentum",0,src)
		critChance = new("Critical Chance", 0, src)
		critDamage = new("Critical Damage", 0, src)
		critChMod = new("Weapon Critical Chance", 0 ,src)
		critDmgMod = new("Weapon Critical Damage", 0, src)
		baseSp = new("Base Speed", 0, src)
		statList = new
		statList += list(level,hp,ele,shield,stam,cons,agil,str,fin,apt,res,sp,defVal,atkVal)
		hidList = new
		hidList += list(level, hp, ele, shield, stam, cons, agil, str, fin, apt, res, sp, armorVal, weaponVal, weaponRange, defVal, atkVal, momentum, critChance, critDamage)
		equipList = new
		inventory = new
		statUpdate(1)

	proc
		statUpdate(var/x)
			defVal.change(res.value*2 + armorVal.value)
			atkVal.change(str.value*2 + weaponVal.value)
			atkVal.maxChange(atkVal.value + weaponRange.value)
			sp.change(baseSp.value + momentum.value*4)
			critChance.change((-(1/(0.015*fin.value + 1)) + 1.15) * 100 + critChMod.value)
			critDamage.change(max(1,(0.1*sqrt(str.value)) + 1) + critDmgMod.value)
			if(x)
				hp.change(cons.value*4 + level.value*10 + 50)
				stam.change(str.value*2 + 20)
	Stat()
		for(var/stat/s in statList)
			if(s.vessel)
				stat("[s.name]", "[s.display(2)]")
			if(s.range)
				stat("[s.name]", "[s.display(3)]")
			if(!s.vessel && !s.range)
				stat("[s.name]", "[s.display(1)]")
		statpanel("Inventory")
		for(var/obj/o in inventory)
			stat("[o.name]")

	verb
		display_hidden()
			for(var/stat/s in hidList)
				if(s.vessel)
					src << "[s.name] [s.display(2)]"
				if(s.range)
					src << "[s.name] [s.display(3)]"
				if(!s.vessel && !s.range)
					src << "[s.name] [s.display(1)]"
proc
	generate_4let()
		var/a = ascii2text(rand(97, 122))
		var/b = ascii2text(rand(48,57))
		var/c = ascii2text(rand(65,90))
		var/d = ascii2text(rand(48,57))
		return "[a][b][c][d]"

	generate_6let()
		var/a = ascii2text(rand(97, 122))
		var/b = ascii2text(rand(97, 122))
		var/c = ascii2text(rand(48,57))
		var/d = ascii2text(rand(65,90))
		var/e = ascii2text(rand(48,57))
		var/f = ascii2text(rand(65,90))
		return "[a][b][c][d][e][f]"

	material_gen(var/n)
		if(n > 0 && n <= 4)
			return "Engineered Steel"
		if(n > 4 && n <= 8)
			return "Carbon-Alloy"
		if(n > 8 && n <= 12)
			return "Kronz"
		if(n > 12 && n <= 16)
			return "Berlitz"
		if(n > 16 && n <= 20)
			return "Refined Titanium"
		if(n > 20 && n <= 24)
			return "Solidified Xenon"

	armor_gen(var/n)
		if(n > 0 && n <= 3)
			return "Compressed Steel"
		if(n > 3 && n <= 6)
			return "Bucket Alloy"
		if(n > 6 && n <= 9)
			return "Reflexive Kronz"
		if(n > 9 && n <= 12)
			return "Hyper Berlitz"
		if(n > 12 && n <= 15)
			return "Refined Compressed Titanium"
		if(n > 15 && n <= 18)
			return "Solidified Conduction Xenon"

	spawnItem(var/typePath, var/location, var/customName, var/customNum)
		var/notNamed
		var/mob/m
		var/area/a
		var/obj/o
		if(!customNum)
			customNum = 1
		var/n = customNum
		if(!customName)
			notNamed = 1
		if(istype(location, /mob))
			m = location
		if(istype(location,/area))
			a = location
		if(ispath(typePath, /obj/item/gear/weapon))
			var/obj/item/gear/g = new typePath(n)
			if(notNamed)
				g.name = "[material_gen(g.numGen(n))] [g.name]"
			o = g
		if(ispath(typePath, /obj/item/gear/equipment))
			var/obj/item/gear/equipment/e = new typePath(n)
			if(notNamed)
				e.name = "[armor_gen(e.numGen(n))] [e.name]"
			o = e
		if(m)
			m.inventory += o
			o.loc = m
		if(a)
			o.loc = a

	spawnSet(var/location, var/customNum)
		spawnItem(/obj/item/gear/equipment/lightArmor/head/helm, location, null, customNum)
		spawnItem(/obj/item/gear/equipment/lightArmor/torso/armor, location, null, customNum)
		spawnItem(/obj/item/gear/equipment/lightArmor/arms/gauntlets, location, null, customNum)
		spawnItem(/obj/item/gear/equipment/lightArmor/legs/leggings, location, null, customNum)
		spawnItem(/obj/item/gear/equipment/lightArmor/feet/boots, location, null, customNum)

mob
	proc
		equipItem()
			var/list/selection = new
			for(var/obj/item/gear/x in inventory)
				selection += x
			var/obj/item/gear/g = input("Which item to equip?") as null|anything in selection
			if(!g)
				return
			if(g.slot == "1hand")
				var/choice = input("Equip in mainhand or offhand?") as null|anything in list("mainhand","offhand")
				if(!choice)
					return
				g.slot = choice
			g.equipProc(src)

		unequipItem()
			var/obj/item/gear/g = input("Which item to unequip?") as null|anything in equipList
			if(!g)
				return
			g.unequipProc(src)
	verb
		equip()
			equipItem()
		unequip()
			unequipItem()



obj
	item
		gear
			var
				val
				slot
			proc
				equipProc(var/mob/user)
					for(var/obj/item/gear/g in user.equipList)
						if(g.slot == src.slot)
							g.unequipProc(user)
					user.equipList += src
					user.inventory -= src
					user << "You have equipped [src]."


				unequipProc(var/mob/user)
					user.equipList -= src
					user.inventory += src
					user << "You have unequipped [src]."
					if(slot == "offhand" || slot == "mainhand")
						slot = "1hand"

				numGen(var/n)
					return n

				function()

				invfunction()



			weapon
				var
					maxVal
					secVal
					basicSpec
				proc
					weaponEffect()

				equipProc(var/mob/user)
					..(user)
					user.weaponVal.perm(val)
					user.weaponRange.perm(maxVal)
					function(user)

				unequipProc(var/mob/user)
					..(user)
					user.weaponRange.perm(-maxVal)
					user.weaponVal.perm(-val)
					invfunction(user)

				sword
					slot = "1hand"
					rapier
						New(var/n)
							name = "Rapier"
							val = rand(n + 3, n + 5)
							maxVal = rand(n + 8, n + 10)
							secVal = rand(5, 15)

						function(var/mob/user)
							user.critChMod.perm(secVal)

						invfunction(var/mob/user)
							user.critChMod.perm(-secVal)

					falchion
						New(var/n)
							name = "Falchion"
							val = rand(n + 6, n + 15)
							maxVal = rand(n + 2, n + 4)
							secVal = rand(1,3) * 0.1

						function(var/mob/user)
							user.critDmgMod.perm(secVal)

						invfunction(var/mob/user)
							user.critDmgMod.perm(-secVal)

					longsword
						New(var/n)
							name = "Longsword"
							val = rand(n + 8, n + 18)
							maxVal = rand(n + 2, n + 4)
				pistol
					slot = "1hand"

					revolver
						New(var/n)
							name = "Revolver"
							val = rand(n + 6, n + 10)
							maxVal = rand(n + 3, n + 5)

					beamPistol
						New(var/n)
							name = "Beam Pistol"
							val = rand(n + 3, n + 8)
							maxVal = rand(n + 5, n + 7)

						function(var/mob/user)
							user.critDmgMod.perm(secVal)

						invfunction(var/mob/user)
							user.critDmgMod.perm(-secVal)





			equipment
				equipProc(var/mob/user)
					..(user)
					user.armorVal.perm(val)

				unequipProc(var/mob/user)
					..(user)
					user.armorVal.perm(-val)

				lightArmor
					head
						helm
							New(var/n)
								name = "Helm"
								val = rand(n + 4, n + 8)
					torso
						armor
							New(var/n)
								name = "Coat"
								val = rand(n + 10, n + 15)
					arms
						gauntlets
							New(var/n)
								name = "Gloves"
								val = rand(n + 5, n + 10)
					legs
						leggings
							New(var/n)
								name = "Leggings"
								val = rand(n + 8, n + 12)
					feet
						boots
							New(var/n)
								name = "Boots"
								val = rand(n + 4, n + 8)


proc
	singleTargetDel(var/obj/skill/o)
		if(!o.BLAST && o.BLAST != 0 && !o.WAVE)
			o.user.hasMoved = 1
			del o

	triCollision(var/mob/first, var/mob/second)
		var/obj/skill/a = first.actingSkill
		var/obj/skill/b = second.actingSkill
		if(a.triangle == "true")
			if(b.triangle == "counter")
				b.counterScene(a.user)
				return second
		if(a.triangle == "feint")
			if(b.triangle == "counter")
				a.feintScene(b.user)
				return first
			if(b.triangle == "true")
				a.feintFailScene(b.user)
				return second
		return first

obj/item/gear/weapon
	proc
		basicAttack(var/mob/user)
			var/obj/skill/s = new basicSpec()
			user.knownSkills += s
obj
	skill

		var
			isAttack = null
			isDefense = null
			mob/user
			targetSpace
			list
				targets
			BLAST
			WAVE
			triangle
			special
			range
			obj/item/gear/weapon/spawnedSignature

		New()
			..()
			targets = new


		proc
			actiMsg()
			igniteSkill(var/mob/M, var/mob/target, var/trianglePlay, var/obj/item/gear/weapon/w)
				var/obj/skill/s = new src.type()
				M.actingSkill = s
				M.actingSkill.activation(M, target)
				if(s)
					s.triangle = trianglePlay
				if(w)
					spawnedSignature = w

			activation(var/mob/self, var/mob/target)
				user = self
				if(isAttack)
					if(!BLAST && BLAST != 0 && !WAVE)
						user.singleTarget(src, target)
					else
						if(BLAST || BLAST == 0)
							user.spaceTarget(src, target)
						else
							user.waveTarget(src, target)

					for(var/mob/M in targets)
						if(M.actingSkill)
							if(M.actingSkill.isDefense)
								clashVsDefense(M)
								continue
							if(M in user.targetedBy)
								clashVsOffense(M)
								user.targetedBy -= M
								continue
						else if (M.hasMoved)
							noClash(M)
							continue
						if(!M.targetedBy)
							M.targetedBy = new
						M.targetedBy += user
						if(!special && !range)
							if(user.client)
								triangle =	user.triangleAttack()
				else if(isDefense)
					actiMsg()
					for(var/mob/M in user.targetedBy)
						clashVsOffense(M)

				for(var/mob/M in user.targetedBy)
					if(M.actingSkill && M.actingSkill.isAttack)
						M.actingSkill.noClash(user)
			dmgScene()
			clashVsDefense()
			clashVsOffense()
			noClash()
			defScene()
			feintScene(var/mob/target)
				world << "[user] feints out [target]'s counter and moves first!"
			feintFailScene(var/mob/target)
				world << "[user]'s feint fails against [target]'s true attack!"
			counterScene(var/mob/target)
				world << "[user] successfully counters [target]'s true attack!"
			strikeMsg(var/mob/target, var/damage, var/element)
				if(!element)
					element = "physical"
				world << "[user] smacks [target] for [damage] points of [element] damage."
			critMsg(var/mob/target)
				world << "Critical hit on [target]!"

		attack
			actiMsg(var/mob/T)
				world << "[user] attempts to smack [T]!"
			isAttack = 1

			clashVsOffense(var/mob/T)
				if(!special && !range && user.client)
					triangle = user.triangleCounter()
				var/mob/target = T
				var/obj/skill/o = T.actingSkill
				var/obj/skill/oo = user.actingSkill
				var/mob/first = target
				var/mob/second = user
				if(!o.range && !o.special && !oo.range && !oo.special)
					first = triCollision(target, user)
					if(user == first)
						second = target
					else
						second = user
				if(user.initiative)
					first = user
					second = target
					if(target.initiative)
						first = target
						second = user
				world << "[first] [second]"
				first.actingSkill.dmgScene(second)
				second.actingSkill.dmgScene(first)
				singleTargetDel(src)
				singleTargetDel(o)

			clashVsDefense(var/mob/T)
				T.actingSkill.defScene(user)

			dmgScene(var/mob/target)
				//hit effects on target
				target.momentum.permChange(0)
				target.initiative = null
				//apply weapon element effect
				var/element
				if(spawnedSignature)
					element = spawnedSignature.weaponEffect(target, "applied")
				//inflict damage
				var/damage = rand(user.atkVal.value, user.atkVal.maxValue)
				world << "[damage]"
				if(prob(user.critChance))
					damage*=2
					critMsg(target)
				//combo increase acensi0n
				damage *= 1 + user.momentum.value * 0.1
				world << "[damage]"
				damage *= 1 - KONSTANT_ArmorNegation(target.defVal.value)
				damage = round(damage)
				strikeMsg(target, damage, element)
				if(target.shield.value && target.shield.value > 0)
					var/leftover = target.shield.value
					target.shield.sub(min(target.shield.value, damage))
					damage = max(0,damage - leftover)
				target.hp.sub(damage)
				//apply user effects
				if(spawnedSignature)
					spawnedSignature.weaponEffect(user, "self")
				user.momentum.perm(4)
				user.initiative = null



			noClash(var/mob/T)
				var/target = T
				world << "[user] smacks [target] straight up BOOM!"


			basicAttack

			basicBlast
				BLAST = 1


		defense
			isDefense = 1

			defScene(var/mob/target)
				world << "[user] avoids [target]'s attack."
				singleTargetDel(target.actingSkill)

			actiMsg()
				world << "[user] takes a defensive stance."

			clashVsOffense(var/mob/T)
				defScene(T)


world
	New()
		mob = /mob/player
		for(var/Atype in typesof (/area)) //this would, for example, loop and name spawnpoint
			var/area/A = new Atype
			if(!A.tagged)
				A.tag = A.name
		..()
area
	var
		north
		northEast
		east
		southEast
		south
		southWest
		west
		northWest
		entranceMsg
		tagged

	Flashpoint
		New()
			..()
		entranceMsg = "<FONT COLOR = blue>Random place to fight and stuff.</FONT>"

mob
	var
		busy = 0 //access switch. 0 for access, 1 for none.


	Move(area/A)
		if(busy)
			return
		..()
		stam.restore()
		shield.restore()
		initiative = 0
		src.momentum.change(0)
		if(A.entranceMsg)
			src << "[A.entranceMsg]"

proc
	KONSTANT_ArmorNegation(var/x)
		world << "[0.065*sqrt(x)] dmg reduction"
		return 0.065*sqrt(x)

mob
	var
		allies
		list
			opponents
			knownSkills
			targetedBy
		direction
		spacing
		initiative
		obj/skill/actingSkill
		hasMoved
	proc
		voidDisplay()
			return "[src] is standing at space [spacing], facing [direction], with [hp.display(1)] Health."

		skillCheck()
			if(!knownSkills)
				knownSkills = new
			var/checkFill = 0
			for(var/obj/item/gear/weapon/w in equipList)
				w.basicAttack(src)
				checkFill++
			if(!checkFill)
				knownSkills += new/obj/skill/attack/basicBlast()
			knownSkills += new /obj/skill/defense()

		skillz()
			if(client)
				var/choice = input("What to do?") as null|anything in list("Fite")
				if(!choice)
					skillz()
					return
				if(choice == "Fite")
					var/obj/skill/s = input("Which attack?") as null|anything in knownSkills
					if(s)
						s.igniteSkill(src)
					else
						skillz()
						return
			else
				botAiCalib()

		botAiCalib()
			var/mob/target
			for(var/mob/M in opponents)
				target = M
			var/obj/skill/s = new/obj/skill/attack()
			s.igniteSkill(src, target, "feint")

		singleTarget(var/obj/skill/s, var/mob/target)
			if(!target)
				target = input("Who to slaughter?") as null|anything in opponents
			s.targets += target
			s.actiMsg(target)

		spaceTarget(var/obj/skill/s, var/target)
			if(!target)
				target = input("Which area to blast?") as null|num
			for(var/mob/M in loc)
				if(M.spacing == target)
					s.targets += M
			s.actiMsg(target)

		waveTarget(var/obj/skill/s, var/target)
			if(!target)
				target = input("Which direction to fire?") as null|anything in list("north","south")
			for(var/mob/M in loc)
				if((M.spacing > src && target == "north") || (M.spacing < src && target == "south"))
					s.targets += M
			s.actiMsg(target)

		triangleAttack()
			var/obj/skill/s = actingSkill
			if(client && !s.BLAST && !s.WAVE)
				var/selection = input("Feint or true?") as null|anything in list("feint","true")
				return selection

		triangleCounter()
			var/obj/skill/s = actingSkill
			if(client && !s.BLAST && !s.WAVE)
				var/selection = input("Counter or true?") as null|anything in list("counter","true")
				return selection

	player
		allies = "player"
battleAdmin
	parent_type = /obj
	var
		list
			teamA
			teamB
			total
	New(var/area/A)
		..()
		loc = A
		teamA = new
		teamB = new
		total = new
		for(var/mob/M in A)
			M.skillCheck()
			total += M
			if(!M.allies)
				M.allies = "enemy"
			if(M.allies == "player")
				teamA += M
				world << "[M] is on teamA!"
				M.opponents = teamB
				M.direction = "north"
				M.spacing = 0
			else
				teamB += M
				world << "[M] is on teamB!"
				M.opponents = teamA
				M.direction = "south"
				M.spacing = 0
		rotation(A)
	proc
		speedCycle(var/list/q) //goddamn insertion sort too strong
			q = total
			var/j
			var/i
			world << "..."
			for(j = 2, j <= q.len, j++)
				var/mob/tempMob = q[j]
				i = j
				while(i > 1)
					var/mob/compareMob = q[i-1]
					if(tempMob.sp.value > compareMob.sp.value)
						q[i] = q[i - 1]
						i--
					else
						break
				q[i] = tempMob
			for(var/mob/M in q)
				world << "[M]"
			total = q


		rotation(var/recursionVal)
			var/i = recursionVal
			if(!i)
				i = 0
			i++
			for(var/mob/M in total)
				M << "Round [i]"
				for(var/mob/MM in total)
					M << MM.voidDisplay()
			speedCycle(total)
			var/grooveMeter = 1
			var/dancer = 0
			while(grooveMeter > 0)
				for(var/mob/M in total)
					if(max(1,M.sp.value/10) > grooveMeter - 1)
						M.skillz()
						dancer++
				if(dancer == 0)
					grooveMeter = -1
				dancer = 0
				grooveMeter++
				for(var/mob/M in total) //rational check on all mobs to reset their values if needed
					M.hasMoved = null




mob
	var
		crippled
		bleeding
		burning
		kD
		channeling
		staggered
		conditions
debuff
	var
		duration
		damage
		mob/targetMob
		mob/inflictingMob
		listingString

	New(var/dur, var/mob/T, var/mob/M)
		..()
		duration = dur
		targetMob = T
		inflictingMob = M
		if(!M.conditions)
			M.conditions = new
		M.conditions.contents += src

	proc
		turnPass()
			duration--
			afflict()
			if(duration == 0)
				remove()

		remove()
			del src

		afflict()

	crippled
		New(var/dur, var/mob/T, var/mob/M)
			..(dur, T, M)
			listingString = "crippled"
			damage = round(targetMob.baseSp.value*0.33)
			targetMob.baseSp.sub(damage)
			world << "[targetMob] has been crippled."

		remove()
			targetMob.baseSp.restore()
			world << "[targetMob] has recovered from cripple."
			..()

	bleeding
		New(var/dur, var/mob/T, var/mob/M, var/obj/item/gear/weapon/spawnedSignature)
			..(dur, T, M)
			listingString = "bleeding"
			damage = round(rand(M.fin.value, M.fin.value + M.str.value))
			damage = spawnedSignature.weaponEffect("bleeding", damage)
			world << "[targetMob] has started bleeding."

		afflict()
			targetMob.hp.sub(damage)
			world << "[targetMob] has taken [damage] points of bleeding damage."

		remove()
			world << "[targetMob] has recovered from bleeding."
			..()

	knockdown
		New(var/mob/T)
			..(null, T, null)
			listingString = "knocked down"
			damage = targetMob.baseSp.value
			targetMob.baseSp.sub(damage)
			world << "[targetMob] has been knocked down."

	channeling
		New(var/mob/M, var/obj/skill/primeSkill)
			..(null, null, M)
			listingString = "channeling"




debuffList
	var
		contents
	proc
		checkFor(var/compareString)
			for(var/debuff/d in contents)
				if(d.listingString == compareString)
					return d
		display()
			//not implemented