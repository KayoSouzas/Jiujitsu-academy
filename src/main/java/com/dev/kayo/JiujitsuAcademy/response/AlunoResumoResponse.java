package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;

public record AlunoResumoResponse (
                                    String nome,
                                    Faixa faixaAtual
) {
}
