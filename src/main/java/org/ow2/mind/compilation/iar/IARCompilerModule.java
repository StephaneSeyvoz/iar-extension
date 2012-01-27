/**
 * 
 * Copyright Assystem 2011
 * 
 * This file is part of "Mind Compiler" is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Author  : Stéphane Seyvoz
 * Contact : sseyvoz@assystem.com 
 * Contributors : julien.tous@orange.com
 */

package org.ow2.mind.compilation.iar;

import org.ow2.mind.CommonBackendModule;
import org.ow2.mind.compilation.CompilerWrapper;
import org.ow2.mind.inject.AbstractMindModule;

/**
 * Guice Module that is intended to override the {@link CommonBackendModule}. It
 * replaces the default implementation of {@link CompilerWrapper} by the
 * {@link IARCompilerModule} class.
 */
public class IARCompilerModule extends AbstractMindModule {

  protected void configureCompilerWrapper() {
    bind(CompilerWrapper.class).to(IARCompilerWrapper.class);
  }
}
