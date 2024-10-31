package com.binteckh.consultacep.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ZipCodeVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Filtra apenas os dígitos e limita a 8 caracteres
        val digits = text.text.filter { it.isDigit() }.take(8)

        // Formata o texto com um traço após o quinto caractere
        val formatted = when {
            digits.length > 5 -> digits.substring(0, 5) + "-" + digits.substring(5)
            else -> digits
        }

        // Cria um mapeamento de offset entre o texto original e o texto transformado
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 5 -> offset
                    offset <= 8 -> offset + 1 // Compensa o traço
                    else -> formatted.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 5 -> offset
                    offset <= 9 -> offset - 1 // Compensa o traço
                    else -> digits.length
                }
            }
        }

        // Retorna o texto transformado com o mapeamento de offsets
        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}