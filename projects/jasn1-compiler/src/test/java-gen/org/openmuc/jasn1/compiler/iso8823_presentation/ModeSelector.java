/**
 * This class file was automatically generated by jASN1 v1.8.1-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.jasn1.compiler.iso8823_presentation;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;


public class ModeSelector implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 17);

	public byte[] code = null;
	public BerInteger modeValue = null;
	
	public ModeSelector() {
	}

	public ModeSelector(byte[] code) {
		this.code = code;
	}

	public ModeSelector(BerInteger modeValue) {
		this.modeValue = modeValue;
	}

	public int encode(BerByteArrayOutputStream os) throws IOException {
		return encode(os, true);
	}

	public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
			if (withTag) {
				return tag.encode(os) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		codeLength += modeValue.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 0
		os.write(0x80);
		codeLength += 1;
		
		codeLength += BerLength.encodeLength(os, codeLength);

		if (withTag) {
			codeLength += tag.encode(os);
		}

		return codeLength;

	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		if (totalLength == -1) {
			subCodeLength += berTag.decode(is);

			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
				modeValue = new BerInteger();
				subCodeLength += modeValue.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			int nextByte = is.read();
			if (berTag.tagNumber != 0 || berTag.tagClass != 0 || berTag.primitive != 0
			|| nextByte != 0) {
				if (nextByte == -1) {
					throw new EOFException("Unexpected end of input stream.");
				}
				throw new IOException("Decoded sequence has wrong end of contents octets");
			}
			codeLength += subCodeLength + 1;
			return codeLength;
		}

		while (subCodeLength < totalLength) {
			subCodeLength += berTag.decode(is);
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
				modeValue = new BerInteger();
				subCodeLength += modeValue.decode(is, false);
			}
		}
		if (subCodeLength != totalLength) {
			throw new IOException("Length of set does not match length tag, length tag: " + totalLength + ", actual set length: " + subCodeLength);

		}
		codeLength += subCodeLength;

		return codeLength;
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = os.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		sb.append("\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (modeValue != null) {
			sb.append("modeValue: ").append(modeValue);
		}
		else {
			sb.append("modeValue: <empty-required-field>");
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}
