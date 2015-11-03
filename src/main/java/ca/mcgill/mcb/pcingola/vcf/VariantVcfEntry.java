package ca.mcgill.mcb.pcingola.vcf;

import java.util.Collection;
import java.util.LinkedList;

import ca.mcgill.mcb.pcingola.interval.Variant;
import ca.mcgill.mcb.pcingola.util.Gpr;

/**
 * Variant + VcfEntry
 * This is used to 'explode' a VcfEntry into all its constituent variants.
 *
 * IMPORTANT: The reason why we need this is because a VcfEntry may have
 * multiple variants (e.g. multiallelic entry). These entries may need
 * to be added multiple times to different nodes in an interval tree.
 *
 * @author pcingola
 */
public class VariantVcfEntry extends Variant {

	private static final long serialVersionUID = 2589022632388179932L;

	protected VcfEntry vcfEntry;

	/**
	 * Create a collection of all <Variant, VcfEntry>
	 */
	public static Collection<VariantVcfEntry> factory(VcfEntry vcfEntry) {
		LinkedList<VariantVcfEntry> list = new LinkedList<>();

		for (Variant var : vcfEntry.variants()) {
			Gpr.debug("Variant: " + var + "\tVcfEntry: " + vcfEntry);
			list.add(new VariantVcfEntry(var, vcfEntry));
		}

		return list;
	}

	public VariantVcfEntry(Variant variant, VcfEntry vcfEntry) {
		super(variant.getParent(), variant.getStart(), variant.getEnd(), variant.getId());

		chromosomeNameOri = variant.getChromosomeNameOri();
		type = variant.getType();
		variantType = variant.getVariantType();
		ref = variant.getReference();
		alt = variant.getAlt();
		genotype = variant.getGenotype();
		imprecise = variant.isImprecise();

		this.vcfEntry = vcfEntry;
	}

	public VcfEntry getVcfEntry() {
		return vcfEntry;
	}

}
