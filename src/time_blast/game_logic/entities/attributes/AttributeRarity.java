package time_blast.game_logic.entities.attributes;

public enum AttributeRarity {
    common(1f),
    uncommon(1.2f),
    rare(1.3f),
    epic(1.4f),
    legendary(1.5f);
    final float DAMAGE_MULTIPLIER;
    AttributeRarity(float DAMAGE_MULTIPLIER){this.DAMAGE_MULTIPLIER = DAMAGE_MULTIPLIER;}
}
