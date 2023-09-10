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
        Background(name = "Acólito", uConcentration = 61, uPersuasion = 61, uHeal = 26, uSearch = 16),
        Background(name = "Acompañante", uPersuasion = 61, uListen = 61, uManualDexterity = 26, uHeal = 16),
        Background(name = "Alguacil", uSpot = 61, uSearch = 61, uListen = 16, meleeProficiency = true),
        Background(name = "Aprendiz"),
        Background(name = "Bailarín", uPerform1 = 61, uPerform2 = 61, uAgility = 61, uAthletics = 26, uPersuasion = 16),
        Background(name = "Cazador", uSearch = 61, uSurvival = 61, uListen = 16, rangedProficiency = true),
        Background(name = "Comerciante", uPersuasion = 61, uPilot = 61, uEmpathy = 26, uSearch = 16),
        Background(name = "Curandero", uHeal = 61, uConcentration = 61, uEmpathy = 26, uPersuasion = 16),
        Background(name = "Erudito", uConcentration = 61, uPersuasion = 61, uSearch = 26, uCrafting1 = 16, uCrafting2 = 16),
        Background(name = "Espía", uStealth = 61, uSpot = 61, uPersuasion = 26, uSearch = 16),
        Background(name = "Guardabosques", uSurvival = 61, uSearch = 61, uEmpathy = 26, uSpot = 16),
        Background(name = "Herrero", uCrafting1 = 61, uAthletics = 26, uManualDexterity = 16, meleeProficiency = true),
        Background(name = "Inventor", uCrafting1 = 61, uCrafting2 = 61, uManualDexterity = 61, uConcentration = 26, uSpot = 16),
        Background(name = "Ladrón", uAgility = 61, uManualDexterity = 61, uStealth = 26, uAthletics = 16),
        Background(name = "Marinero", uPilot = 61, uAthletics = 61, uSurvival = 16, meleeProficiency = true),
        Background(name = "Mensajero", uMount = 61, uAthletics = 61, uSearch = 26, uEmpathy = 16),
        Background(name = "Mercenario", uSurvival = 16, uAthletics = 16, rangedProficiency = true, meleeProficiency = true),
        Background(name = "Músico", uPerform1 = 61, uPerform2 = 61, uListen = 61, uPersuasion = 26, uManualDexterity = 16),
        Background(name = "Noble", uMount = 61, uPersuasion = 61, uEmpathy = 16, meleeProficiency = true),
        Background(name = "Vagabundo", uAthletics = 61, uSurvival = 61, uSearch = 26, uSpot = 16)
        )
}