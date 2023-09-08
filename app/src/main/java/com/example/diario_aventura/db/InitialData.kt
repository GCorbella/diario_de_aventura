package com.example.diario_aventura.db

import com.example.diario_aventura.db.entities.Background
import com.example.diario_aventura.db.entities.Race
import com.example.diario_aventura.enums.Proficiencies
import com.example.diario_aventura.enums.Skills

object InitialData {
    val initialRaces = listOf(
        Race(name="Aasimar (Angel)", size = "Mediano", strength = 2, intelligence = -2, charisma = 2, bProficiency = Proficiencies.SWORDS, bSkill = Skills.EMPATHY),
        Race(name="Aasimar (Azata)", size = "Mediano", dexterity = 2, wisdom = -2, charisma = 2, bProficiency = Proficiencies.BOWS, bSkill = Skills.AGILITY),
        Race(name="Aasimar (Arconte)", size = "Mediano", dexterity = -2, wisdom = 2, charisma = 2, bProficiency = Proficiencies.SPEARS, bSkill = Skills.PERSUASION),
        Race(name="Elfo (Imperial)", size = "Mediano", dexterity = 2, constitution = -2, wisdom = 2, bProficiency = Proficiencies.SWORDS, bSkill = Skills.CONCENTRATION),
        Race(name="Elfo (Insular)", size = "Mediano", dexterity = 2, constitution = -2, intelligence = 2, bProficiency = Proficiencies.BOWS, bSkill = Skills.SURVIVAL),
        Race(name="Elfo (Oscuro)", size = "Mediano", dexterity = 2, intelligence = 2, charisma = -2, bProficiency = Proficiencies.DAGGERS, bSkill = Skills.STEALTH),
        Race(name="Enano (Montañas)", size = "Mediano", strength = 2, constitution = 2, charisma = -2, bProficiency = Proficiencies.MACES, bSkill = Skills.CRAFTING_1),
        Race(name="Enano (Superficie)", size = "Mediano", strength = 2, constitution = 2, wisdom = -2, bProficiency = Proficiencies.AXES, bSkill = Skills.CRAFTING_1),
        Race(name="Enano (Oscuro)", size = "Mediano", constitution = 4, charisma = -2, bProficiency = Proficiencies.CROSSBOWS, bSkill = Skills.STEALTH),
        Race(name="Gnomo (Insular)", size = "Pequeño", strength = -2, intelligence = 4, bProficiency = Proficiencies.SHORT_FIREARMS, bSkill = Skills.CRAFTING_1),
        Race(name="Gnomo (Oscuro)", size = "Pequeño", intelligence = 4, charisma = -2, bProficiency = Proficiencies.DAGGERS, bSkill = Skills.STEALTH),
        Race(name="Humano", size = "Mediano", bProficiency = Proficiencies.CHOOSE, bSkill = Skills.CHOOSE),
        Race(name="Mediano (Nómada)", size = "Pequeño", constitution = 4, wisdom = -2, bProficiency = Proficiencies.CROSSBOWS, bSkill = Skills.MANUAL_DEXTERITY),
        Race(name="Mediano (Urbano)", size = "Pequeño", dexterity = 2, constitution = 2, wisdom = -2, bProficiency = Proficiencies.DAGGERS, bSkill = Skills.PERFORM_1),
        Race(name="Orco (Verde)", size = "Mediano", strength = 4, intelligence = -2, bProficiency = Proficiencies.AXES, bSkill = Skills.ATHLETICS),
        Race(name="Orco (Rojo)", size = "Mediano", strength = 4, wisdom = -2, bProficiency = Proficiencies._2HAXES, bSkill = Skills.SURVIVAL),
        Race(name="Orco (Negro)", size = "Mediano", strength = 4, charisma = -2, bProficiency = Proficiencies.UNARMED, bSkill = Skills.ATHLETICS),
        Race(name="Semi-Elfo", size = "Mediano", dexterity = 2, constitution = -2, bProficiency = Proficiencies.CHOOSE, bSkill = Skills.CHOOSE),
        Race(name="Semi-Orco", size = "Mediano", strength = 2, intelligence = -2, bProficiency = Proficiencies.CHOOSE, bSkill = Skills.CHOOSE),
        Race(name="Tiflin (Daemon)", size = "Mediano", dexterity = 2, wisdom = 2, charisma = -2, bProficiency = Proficiencies.AXES, bSkill = Skills.EMPATHY),
        Race(name="Tiflin (Demonio)", size = "Mediano", dexterity = 2, constitution = 2, charisma = -2, bProficiency = Proficiencies.SPEARS, bSkill = Skills.AGILITY),
        Race(name="Tiflin (Diablo)", size = "Mediano", dexterity = 2, constitution = -2, charisma = 2, bProficiency = Proficiencies.DAGGERS, bSkill = Skills.PERSUASION),
        Race(name="Trasgo (Común)", size = "Pequeño", strength = 2, dexterity = 2, constitution = 2, intelligence = -4, bProficiency = Proficiencies.SPEARS, bSkill = Skills.SURVIVAL),
        Race(name="Trasgo (Oscuro)", size = "Pequeño", strength = 2, dexterity = 2, constitution = 2, charisma = -4, bProficiency = Proficiencies.BOWS, bSkill = Skills.STEALTH),
        Race(name="Estrígidos", size = "Mediano", strength = -2, dexterity = -2, wisdom = 6, bProficiency = Proficiencies.PROJECTILE_SPELLS, bSkill = Skills.CONCENTRATION),
        Race(name="Falcónidos", size = "Mediano", dexterity = 4, constitution = -2, wisdom = -2, charisma = 2, bProficiency = Proficiencies.BOWS, bSkill = Skills.SPOT),
        Race(name="Ánidos", size = "Mediano", strength = -4, dexterity = 2, wisdom = 2, charisma = 2, bProficiency = Proficiencies.THROWING_WEAPONS, bSkill = Skills.HEAL),
        Race(name="Lupinos", size = "Mediano", dexterity = 2, constitution = -2, wisdom = 4, charisma = -2, bProficiency = Proficiencies.CROSSBOWS, bSkill = Skills.SURVIVAL),
        Race(name="Vulpinos", size = "Mediano", strength = -2, dexterity = 2, wisdom = 4, charisma = -2, bProficiency = Proficiencies.RAY_SPELLS, bSkill = Skills.STEALTH),
        Race(name="Cánidos", size = "Mediano", dexterity = -2, constitution = 2, intelligence = -2, wisdom = 4, bProficiency = Proficiencies.MACES, bSkill = Skills.SEARCH),
        Race(name="Panthera", size = "Mediano", dexterity = 4, constitution = -2, intelligence = 2, charisma = -2, bProficiency = Proficiencies.SPEARS, bSkill = Skills.AGILITY),
        Race(name="Leo", size = "Mediano", strength = 2, dexterity = 4, constitution = -2, intelligence = -2, bProficiency = Proficiencies._2HAXES, bSkill = Skills.PERSUASION),
        Race(name="Lynx", size = "Mediano", strength = -2, dexterity = 4, wisdom = 2, charisma = -2, bProficiency = Proficiencies.BOWS, bSkill = Skills.STEALTH),
        Race(name="Eslizón", size = "Pequeño", dexterity = 6, constitution = -4, bProficiency = Proficiencies.THROWING_WEAPONS, bSkill = Skills.MANUAL_DEXTERITY),
        Race(name="Kroxigor", size = "Mediano", strength = 6, intelligence = -4, bProficiency = Proficiencies._2HMACES, bSkill = Skills.SURVIVAL),
        Race(name="Saurio", size = "Mediano", strength = 2, dexterity = 2, constitution = 2, wisdom = -4, bProficiency = Proficiencies.MACES, bSkill = Skills.ATHLETICS)
        )

    val initialBackgrounds = listOf(
        Background(name = "Acólito", skill1 = Skills.CONCENTRATION, skill2 = Skills.PERSUASION, skill3 = Skills.HEAL, skill4 = Skills.SEARCH),
        Background(name = "Acompañante", skill1 = Skills.PERSUASION, skill2 = Skills.LISTEN, skill3 = Skills.MANUAL_DEXTERITY, skill4 = Skills.HEAL),
        Background(name = "Alguacil", skill1 = Skills.SPOT, skill2 = Skills.SEARCH, skill4 = Skills.LISTEN, meleeProficiency = true),
        Background(name = "Aprendiz", skill1 = Skills.CHOOSE, skill2 = Skills.CHOOSE, skill3 = Skills.CHOOSE, skill4 = Skills.CHOOSE, skill5 = Skills.CHOOSE, skill6 = Skills.CHOOSE),
        Background(name = "Bailarín", skill1 = Skills.PERFORM_1, skill2 = Skills.AGILITY, skill3 = Skills.ATHLETICS, skill4 = Skills.PERSUASION),
        Background(name = "Cazador", skill1 = Skills.SEARCH, skill2 = Skills.SURVIVAL, skill4 = Skills.LISTEN, rangedProficiency = true),
        Background(name = "Comerciante", skill1 = Skills.PERSUASION, skill2 = Skills.PILOT, skill3 = Skills.EMPATHY, skill4 = Skills.SEARCH),
        Background(name = "Curandero", skill1 = Skills.HEAL, skill2 = Skills.CONCENTRATION, skill3 = Skills.EMPATHY, skill4 = Skills.PERSUASION),
        Background(name = "Erudito", skill1 = Skills.CONCENTRATION, skill2 = Skills.SEARCH, skill3 = Skills.PERSUASION, skill4 = Skills.CRAFTING_1),
        Background(name = "Espía", skill1 = Skills.STEALTH, skill2 = Skills.SPOT, skill3 = Skills.PERSUASION, skill4 = Skills.SEARCH),
        Background(name = "Guardabosques", skill1 = Skills.SURVIVAL, skill2 = Skills.SEARCH, skill3 = Skills.EMPATHY, skill4 = Skills.SPOT),
        Background(name = "Herrero", skill1 = Skills.CRAFTING_1, skill2 = Skills.ATHLETICS, skill4 = Skills.MANUAL_DEXTERITY, meleeProficiency = true),
        Background(name = "Inventor", skill1 = Skills.CRAFTING_1, skill2 = Skills.MANUAL_DEXTERITY, skill3 = Skills.CONCENTRATION, skill4 = Skills.SPOT),
        Background(name = "Ladrón", skill1 = Skills.AGILITY, skill2 = Skills.MANUAL_DEXTERITY, skill3 = Skills.STEALTH, skill4 = Skills.ATHLETICS),
        Background(name = "Marinero", skill1 = Skills.PILOT, skill2 = Skills.ATHLETICS, skill4 = Skills.SURVIVAL, meleeProficiency = true),
        Background(name = "Mensajero", skill1 = Skills.MOUNT, skill2 = Skills.ATHLETICS, skill3 = Skills.SEARCH, skill4 = Skills.EMPATHY),
        Background(name = "Mercenario", skill4 = Skills.SURVIVAL, skill6 = Skills.ATHLETICS, rangedProficiency = true, meleeProficiency = true),
        Background(name = "Músico", skill1 = Skills.PERFORM_1, skill2 = Skills.LISTEN, skill3 = Skills.PERSUASION, skill4 = Skills.MANUAL_DEXTERITY),
        Background(name = "Noble", skill1 = Skills.MOUNT, skill2 = Skills.PERSUASION, skill4 = Skills.EMPATHY, meleeProficiency = true),
        Background(name = "Vagabundo", skill1 = Skills.ATHLETICS, skill2 = Skills.SURVIVAL, skill3 = Skills.SEARCH, skill4 = Skills.SPOT),
        )
}